package com.zomko.ean

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let {
                    // Internal domain - load in app
                    if (it.startsWith("https://scan.zmk.hu")) {
                        return false
                    } else {
                        // External links - open in default browser
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                        startActivity(intent)
                        return true
                    }
                }
                return false
            }
        }

        webView.loadUrl("https://scan.zmk.hu")
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}