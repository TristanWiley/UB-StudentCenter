package com.tristanwiley.ubstudentcenter.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.tristanwiley.ubstudentcenter.App
import com.tristanwiley.ubstudentcenter.R
import kotlinx.android.synthetic.main.activity_login_webview.*
import okhttp3.Request
import org.jetbrains.anko.doAsync


class LoginWebview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_webview)

        //Load the UB site with a custom UserAgent and Javascript enabled.
        loginWebView.loadUrl("https://myub.buffalo.edu/")
        loginWebView.settings.userAgentString = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36\n"
        loginWebView.settings.javaScriptEnabled = true

        //Manage what happens when the page finishes loading
        loginWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String) {
                super.onPageFinished(view, url)
                //If the login was successful
                if (url.contains("myub.html")) {
                    //TODO go to new Intent
                    finish()
                }
            }

        }
    }
}
