package com.tanvi.newsfresh

import android.content.Context
import android.content.Intent
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.tanvi.newsfresh.Model.Article
import com.tanvi.newsfresh.Utils.DateFormat
import com.tanvi.newsfresh.Utils.randomDrawbleColor

class RvAdapter(var article: List<Article>,var context: Context):RecyclerView.Adapter<RvAdapter.MyViewHolder>() {
    var manager: FragmentManager? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_row_layout, null)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(randomDrawbleColor)
        requestOptions.error(randomDrawbleColor)
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
        val model = article[position]
        val url = model.url
        val text=model.title
        val datee = DateFormat(model.date)
        myViewHolder.tle.text = model.title
        myViewHolder.desc.text = model.description
        myViewHolder.date.text = datee
        myViewHolder.source.text = model.source!!.name
        myViewHolder.frame.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
              Intent(context, TextConverter::class.java)
            intent.putExtra("news detail", url)
            intent.putExtra("title",text)
            context.startActivity(intent)
        }
        Glide.with(context).load(model.urlToImage).into(myViewHolder.imageView)

        myViewHolder.share.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)

            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
            shareIntent.putExtra(Intent.EXTRA_TEXT, url)


            context.startActivity(Intent.createChooser(shareIntent, "choose one"))
        }

    }
    override fun getItemCount(): Int {
        return article.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var tts: TextToSpeech? = null
        var tle: TextView
        var desc: TextView
        var date: TextView
        var source: TextView
        var imageView: ImageView
        var progressBar: ProgressBar? = null
        var frame: FrameLayout
        var share: ImageButton
        var btnVolume :ImageButton
        lateinit var onItemClickListener: OnItemClickListener
    interface OnItemClickListener {
        fun onItemClick(v: View?, position: Int)
    }
    init {
        tle = itemView.findViewById(R.id.title)
        date = itemView.findViewById(R.id.date)
        desc = itemView.findViewById(R.id.descr)
        source = itemView.findViewById(R.id.tvSource)
        imageView = itemView.findViewById(R.id.iv)
        share = itemView.findViewById(R.id.btnShare)
        frame = itemView.findViewById(R.id.framee)
        btnVolume =itemView.findViewById(R.id.btnVolume)
    }
}
}