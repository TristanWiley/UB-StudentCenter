package com.tristanwiley.ubstudentcenter.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tristanwiley.ubstudentcenter.R
import kotlinx.android.synthetic.main.activity_login_webview.*

class LoginWebview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_webview)

        loginWebView.loadUrl("https://myub.buffalo.edu/")
    }
}
