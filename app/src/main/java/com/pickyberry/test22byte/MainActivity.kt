package com.pickyberry.test22byte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.Toast
import com.pickyberry.test22byte.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        loadContent()
        setContentView(binding.root)
    }


    private fun loadContent(){
        binding.webview.webViewClient = WebViewClient()
        CoroutineScope(Dispatchers.Main).launch {

            while (!InternetValidation.hasInternetConnection(application)) {
                Toast.makeText(
                    this@MainActivity,
                    "No internet connection! Retrying...",
                    Toast.LENGTH_SHORT
                ).show()
                delay(3000)
            }

            binding.loadingBar.visibility = View.GONE
            binding.webview.visibility = View.VISIBLE
            binding.webview.loadUrl("https://google.com")

        }
    }
}