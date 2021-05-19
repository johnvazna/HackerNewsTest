package com.hackernews.app.data.hit.data_source.remote.model.dto

import com.google.gson.annotations.SerializedName

/** */
data class StoryTitleResponse(
    @SerializedName("value") val value: String?,
    @SerializedName("matchLevel") val matchLevel: String?,
    @SerializedName("matchedWords") val matchedWords: List<String>?,
)