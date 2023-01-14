package com.tanvi.newsfresh.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.tanvi.newsfresh.R
//import com.tanvi.newsfresh.activity.MainActivity.Companion.dataNew
import com.tanvi.newsfresh.fragment.EntertainmentFragment
import com.tanvi.newsfresh.interfaces.NewsClickInterface
//import com.tanvi.newsfresh.fragment.HealthCareFragment.Companion.dataNew
import com.tanvi.newsfresh.model.Article

    class SecondActivity:AppCompatActivity() {
    lateinit var secondImageView:ImageView
    lateinit var tvSecondText:TextView
    lateinit var tvTitle:TextView
    lateinit var tvAuthor:TextView
    lateinit var sCard :CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //sCard.setBackgroundResource(R.drawable.custom_cardview)
        secondImageView=findViewById(R.id.secondImageView)
        //sCard =findViewById(R.id.sCard)
        tvSecondText =findViewById(R.id.tvSecondText)
        tvTitle =findViewById(R.id.tvTitle)
        tvAuthor =findViewById(R.id.tvAuthor)

        tvSecondText.text=intent.getStringExtra("articleLive")
        tvTitle.text = intent.getStringExtra("articleLink")
        tvAuthor.text = intent.getStringExtra("articleLivi")
        val imageUrl:String?=intent?.extras?.getString("articleLinki")
        if (imageUrl!=null){
            Glide .with(this).load(imageUrl).into(secondImageView)
        }

        }

    }