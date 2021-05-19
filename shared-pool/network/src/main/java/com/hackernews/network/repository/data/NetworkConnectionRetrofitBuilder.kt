package com.hackernews.network.repository.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class NetworkConnectionRetrofitBuilder {

    /* */
    private val baseUrl: String = "https://clients3.google.com/"

    /* */
    private val timeOut: Long = 100L

    /** */
    fun build(): Retrofit =
        Retrofit.Builder()
            .client(buildHttpClient())
            .baseUrl(baseUrl)
            .build()

    /** */
    private fun buildHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .build()

}