package com.hackernews.app.utils

import retrofit2.HttpException
import retrofit2.Response

/** */
@Throws(HttpException::class, Exception::class)
suspend fun <T> retrofitApiCall(apiCall: suspend () -> Response<T>) : T {
    val response = apiCall()
    return if(response.isSuccessful)
        response.body()!!
    else throw HttpException(response)
}