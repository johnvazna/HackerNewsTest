package com.hackernews.app.data.remote.entity

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/** */
data class CommentTextResponse(
    @SerializedName("value") val value: String? = null,
    @SerializedName("matchLevel") val matchLevel: String? = null,
    @SerializedName("fullyHighlighted") val fullyHighlighted: Boolean? = null,
    @SerializedName("matchedWords") val matchedWords: ArrayList<String>
)