package com.hackernews.app.utils

import retrofit2.HttpException
import retrofit2.Response

/** */
@Throws(HttpException::class, Exception::class)
suspend fun <T> baseDataSource(apiCall: suspend () -> Response<T>) : T {
    val response = apiCall()
    return if(response.isSuccessful)
        response.body()!!
    else throw HttpException(response)
}

/** */
fun Exception.messageOrClassName(): String =
    message ?: javaClass.simpleName
