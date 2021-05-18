package com.hackernews.app.data.remote.entity

import com.google.gson.annotations.SerializedName

/** */
data class CommentTextResponse(
    @SerializedName("value") val value: String?,
    @SerializedName("matchLevel") val matchLevel: String?,
    @SerializedName("fullyHighlighted") val fullyHighlighted: Boolean?,
    @SerializedName("matchedWords") val matchedWords: List<String>?
)