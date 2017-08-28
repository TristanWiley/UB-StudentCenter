package com.tristanwiley.ubstudentcenter.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tristanwiley.ubstudentcenter.R
import org.jetbrains.anko.defaultSharedPreferences

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.title = "Log In"

        //If user is already logged in, go to MainActivity
        val isLoggedIn = defaultSharedPreferences.getBoolean("loggedIn", false)
        if(isLoggedIn){
            //TODO go to next activity
            finish()
        }

    }
}
