package com.tristanwiley.ubstudentcenter.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tristanwiley.ubstudentcenter.App
import com.tristanwiley.ubstudentcenter.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.defaultSharedPreferences

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.title = "Log In"

        //If user is already logged in, gLoginActivityLoginActivityo to MainActivity
        val isLoggedIn = defaultSharedPreferences.getBoolean(App.PREF_LOGGED_IN, false)
        if (isLoggedIn) {
            //TODO go to next activity
            finish()
        }

        //Open LoginWebView for logging in and cookie storing
        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginWebview::class.java))
        }

    }
}
