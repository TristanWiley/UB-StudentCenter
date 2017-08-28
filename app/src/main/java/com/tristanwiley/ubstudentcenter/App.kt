package com.tristanwiley.ubstudentcenter

import android.app.Application
import android.webkit.CookieManager
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient


/**
 * Application class that manages certain constants for us.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //Set the app context
        instance = this

        //Create an OkHttpClient with a persistent CookieJar using CookieManager.
        client = OkHttpClient.Builder()
                .cookieJar(object : CookieJar {
                    //Get instance of CookieManager
                    val cookieManager = CookieManager.getInstance()

                    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                        //Get all cookies as an array of Strings (String[])
                        val cookiesArr = cookieManager.getCookie(url.url().toString()).split(";")
                        cookiesArr.forEach {
                            //Add cookie to CookieManager
                            cookieManager.setCookie(url.url().path, it)
                        }
                    }

                    override fun loadForRequest(url: HttpUrl): List<Cookie> {
                        //Get all cookies as an array of Strings (String[])
                        val cookiesArr = cookieManager.getCookie(url.url().toString()).split(";")
                        //Return all cookies mapped to a list of Cookies
                        return cookiesArr.mapNotNull { Cookie.parse(url, it) }
                    }
                })
                .build()
    }

    companion object {

        /**
         * Key for determining if the user is logged in
         */
        val PREF_LOGGED_IN = "loggedIn"

        /**
         * The application context.
         */
        lateinit var instance: App
            private set

        /**
         * Client for OkHttp, used for authenticated request
         */
        lateinit var client: OkHttpClient
    }
}