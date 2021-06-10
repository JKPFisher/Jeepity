package com.example.jeepitybasic

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Credentials
import android.net.NetworkInfo
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.translate.Translate
import com.google.cloud.translate.TranslateOptions
import kotlinx.android.synthetic.main.activity_offline_translation.*
import java.io.IOException
import java.lang.ProcessBuilder.Redirect.from

class OfflineTranslation : AppCompatActivity() {
    private var translate: Translate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_translation)

        translateButton.setOnClickListener {
            if (checkInternetConnection()) {

                //If there is internet connection, get translate service and start translation:
                getTranslateService()
                translate()

            } else {

                //If not, display "no connection" warning:
                translatedTv!!.text = resources.getString(R.string.no_connection)
            }
        }
    }

    private fun getTranslateService() {

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        try {
            resources.openRawResource(R.raw.credentials).use { `is` ->
                val myCredentials = GoogleCredentials.fromStream(`is`)
                val translateOptions = TranslateOptions.newBuilder().setCredentials(myCredentials).build()
                translate = translateOptions.service
            }
        } catch (ioe: IOException) {
            ioe.printStackTrace()

        }

    }

    private fun translate() {

        //Get input text to be translated:
        val originalText: String = inputToTranslate!!.text.toString()
        val translation = translate!!.translate(originalText, Translate.TranslateOption.targetLanguage("tl"), Translate.TranslateOption.model("base"))

        //Translated text and original text are set to TextViews:
        translatedTv!!.text = translation.translatedText

    }

    private fun checkInternetConnection(): Boolean {

        //Check internet connection:
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo

        //Means that we are connected to a network (mobile or wi-fi)
        return activeNetwork?.isConnected == true

    }


}


