package com.example.traveler.data.network.responses

import android.content.Context
import android.net.ConnectivityManager
import com.example.traveler.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response
class NetworkConnectionInterceptor(
    context : Context
) : Interceptor {
    private val applicationContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
    if (!isInternetAvailable()){
        throw NoInternetException("Make sure you have an Active Internet Connection!")
    }
        return chain.proceed(chain.request())
    }
    private fun isInternetAvailable() : Boolean{
        // use system services to get the internet activity
        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetwork.also {
            return it != null
        }
    }
}