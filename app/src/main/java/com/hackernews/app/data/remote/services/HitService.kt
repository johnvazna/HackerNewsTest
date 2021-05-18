package com.hackernews.app.data.remote.services

import com.hackernews.app.data.remote.entity.HitResponse
import retrofit2.Response
import retrofit2.http.GET

interface HitService {
    @GET("search_by_date?query=android")
    suspend fun getHits(): Response<HitResponse>
}