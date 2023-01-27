package com.tanvi.newsfresh

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class TextConverter:AppCompatActivity(),TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private var btnVolume: ImageButton? = null
    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_converter)
        btnVolume = findViewById(R.id.btnVolume)
        textView=findViewById(R.id.tv_textConverter)
        btnVolume!!.isEnabled = false
        // TextToSpeech(Context: this, OnInitListener: this)
        tts = TextToSpeech(this, this)
        intent=getIntent()
        var textView = this.intent.getStringExtra("title")

        btnVolume!!.setOnClickListener { speakOut() }
    }
    private fun speakOut() {
        val text = textView!!.text.toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language not supported!")
            } else {
                btnVolume!!.isEnabled = true
            }
        }
    }
    public override fun onDestroy() {
        // Shutdown TTS when
        // activity is destroyed
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}