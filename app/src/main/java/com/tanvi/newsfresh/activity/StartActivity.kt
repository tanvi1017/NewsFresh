package com.tanvi.newsfresh.activity

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AccelerateInterpolator
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.tanvi.newsfresh.ItemData
import com.tanvi.newsfresh.R
import java.util.*

class StartActivity:AppCompatActivity() {
    lateinit var viewpager:ViewPager
    lateinit var recyclerView: RecyclerView
    lateinit var cardBoll: CardView
    private var currentSpeed = 1f
    var screenHeight = 1
    private  var currentPage =0
    private var NUM_PAGES = 5
    lateinit var edSearch:EditText
    lateinit var searchCardView:CardView
    private lateinit var iv1:ImageView
    private lateinit var iv2:ImageView
    private lateinit var iv3:ImageView
    private lateinit var iv4:ImageView
    private lateinit var iv5:ImageView
    // lateinit var imageButton:ImageButton
    lateinit var cardView1: CardView
    private val defaultAnimationDuration = 1000L
    val listData = mutableListOf<ItemData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        edSearch =findViewById(R.id.edSearch)
        searchCardView =findViewById(R.id.searchCardView)
        recyclerView = findViewById(R.id.recyclerview1)
        cardBoll = findViewById(R.id.cardBoll)
        viewpager = findViewById(R.id.viewPager)
        val images = listOf(R.drawable.iv21, R.drawable.iv27, R.drawable.iv5)
     //   viewpager.adapter = adapterClass
        iv1 = findViewById(R.id.iv1)
        iv2 = findViewById(R.id.iv2)
        iv3 = findViewById(R.id.iv3)
        iv4 = findViewById(R.id.iv4)
        iv5 = findViewById(R.id.iv5)
        StartAnimation()
        // setAdapter()
        FormRecyclerview()
        setListeners()
            iv1 = findViewById(R.id.iv1)
            iv2 = findViewById(R.id.iv2)
            iv3 = findViewById(R.id.iv3)
            iv4 = findViewById(R.id.iv4)
            iv5 = findViewById(R.id.iv5)
            StartAnimation()
            // setAdapter()
            FormRecyclerview()
            setListeners()
//             viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//                override fun onPageScrollStateChanged(state: Int) {
//                    changeColor()
//                    super.onPageScrollStateChanged(state)
//                }
//                override fun onPageScrolled(
//                    position: Int, positionOffset: Float, positionOffsetPixels: Int
//                ) {
//                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//                    changeColor()
//                }
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
//                }
//            })
        val handler = Handler()
        val update = Runnable {
            if (currentPage === NUM_PAGES) {
                currentPage = 0
            }
            viewpager.setCurrentItem(currentPage++, true)
        }
        Timer().schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, 100, 500)
    }
    private fun setAdapter() {
//        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
//        val adapter = Adapter(listData,  this)
//        recyclerView.adapter = adapter
    }
    private fun FormRecyclerview() {
        val data1 = ItemData(R.drawable.ic_biotech_24, "technology")
        val data2 = ItemData(R.drawable.ic_science, "Science")
        val data3 = ItemData(R.drawable.ic_sport, "Sport")
        val data4 = ItemData(R.drawable.ic_baseline_health_and_safety_24, "healthCare")
        val data5 = ItemData(R.drawable.ic_baseline_trending_up_24, "Trending")
        val data6 = ItemData(R.drawable.ic_baseline_insert_emoticon_24, "entertainment")
        listData.add(data1)
        listData.add(data2)
        listData.add(data3)
        listData.add(data4)
        listData.add(data5)
        listData.add(data6)
    }
    private fun StartAnimation() {
        val valueAnimator = ValueAnimator.ofFloat(0f, (screenHeight + 3000).toFloat())
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            cardBoll.translationY = value
        }
        valueAnimator.interpolator = AccelerateInterpolator(currentSpeed)
        valueAnimator.duration = defaultAnimationDuration
        valueAnimator.start()
        setAdapter()
    }

    fun changeColor() {
            when (viewpager.currentItem) {
                0 -> {
                    iv1.setBackgroundColor(applicationContext.resources.getColor(R.color.teal_700))
                    iv2.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv3.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv4.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv5.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                }
                1 -> {
                    iv1.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv2.setBackgroundColor(applicationContext.resources.getColor(R.color.teal_700))
                    iv3.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv4.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv5.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                }
                2 -> {
                    iv1.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv2.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv3.setBackgroundColor(applicationContext.resources.getColor(R.color.teal_700))
                    iv4.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv5.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                }
                3 -> {
                    iv1.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv2.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv3.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv4.setBackgroundColor(applicationContext.resources.getColor(R.color.teal_700))
                    iv5.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                }
                4 -> {
                    iv1.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv2.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv3.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv4.setBackgroundColor(applicationContext.resources.getColor(R.color.purple_200))
                    iv5.setBackgroundColor(applicationContext.resources.getColor(R.color.teal_700))
                }
            }
        }
    private fun setListeners(){
      searchCardView.setOnClickListener {
          val intent =Intent(this@StartActivity,SearchActivity::class.java)
         if (edSearch.text.toString()!=null) {
             val searchText = edSearch.text
             intent.putExtra("searchText", searchText)
         }
          else{}
          startActivity(intent)
      }
    }
}







