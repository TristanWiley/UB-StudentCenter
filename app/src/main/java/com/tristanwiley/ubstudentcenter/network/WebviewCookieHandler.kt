package com.tristanwiley.ubstudentcenter.network

import android.webkit.CookieManager
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import java.util.*

/**
 * Provides a synchronization point between the WebView cookie store and okhttp3.OkHttpClient cookie store
 * From https://gist.github.com/justinthomas-syncbak/cd29feebd6837d5b45f6576c73faedac
 * Converted to Kotlin by TristanWiley
 */
class WebviewCookieHandler : CookieJar {
    private val webviewCookieManager = CookieManager.getInstance()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val urlString = url.toString()

        for (cookie in cookies) {
            webviewCookieManager.setCookie(urlString, cookie.toString())
        }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val urlString = url.toString()
        val cookiesString = webviewCookieManager.getCookie(urlString)

        if (cookiesString != null && !cookiesString.isEmpty()) {
            //We can split on the ';' char as the cookie manager only returns cookies
            //that match the url and haven't expired, so the cookie attributes aren't included
            val cookieHeaders = cookiesString.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val cookies = ArrayList<Cookie>(cookieHeaders.size)

            cookieHeaders.mapNotNullTo(cookies) { Cookie.parse(url, it) }

            return cookies
        }

        return emptyList()
    }
}
