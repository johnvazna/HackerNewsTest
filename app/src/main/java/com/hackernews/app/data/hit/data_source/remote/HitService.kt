package com.hackernews.app.data.hit.data_source.remote

import com.hackernews.app.data.hit.data_source.remote.model.dto.HitResponse
import retrofit2.Response
import retrofit2.http.GET

interface HitService {
    @GET("search_by_date?query=android")
    suspend fun getHits(): Response<HitResponse>
}