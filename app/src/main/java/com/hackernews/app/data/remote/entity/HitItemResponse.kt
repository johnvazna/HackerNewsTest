package com.hackernews.app.data.remote.entity

import com.google.gson.annotations.SerializedName

data class HitItemResponse(
    @SerializedName("created_at") val created_at: String,
    @SerializedName("title") val title: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("points") val points: Long? = null,
    @SerializedName("story_text") val story_text: String? = null,
    @SerializedName("comment_text") val comment_text: String? = null,
    @SerializedName("num_comments") val num_comments: Long? = null,
    @SerializedName("story_id") val story_id: Long? = null,
    @SerializedName("story_title") val story_title: String? = null,
    @SerializedName("story_url") val story_url: String? = null,
    @SerializedName("parent_id") val parent_id: Long? = null,
    @SerializedName("created_at_i") val created_at_i: Long? = null,
    @SerializedName("_tags") val _tags: ArrayList<String>? = null,
    @SerializedName("objectID") val objectID: String? = null,
    @SerializedName("_highlightResult") val _highlightResult: HighlightResultResponse,
)