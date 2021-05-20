package com.hackernews.app.data.hit.data_source.remote.model.dto

import com.google.gson.annotations.SerializedName

data class HitItemResponse(
    @SerializedName("created_at") val created_at: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("points") val points: Int?,
    @SerializedName("story_text") val story_text: String?,
    @SerializedName("comment_text") val comment_text: String?,
    @SerializedName("num_comments") val num_comments: Int?,
    @SerializedName("story_id") val story_id: Long?,
    @SerializedName("story_title") val story_title: String?,
    @SerializedName("story_url") val story_url: String?,
    @SerializedName("parent_id") val parent_id: Long?,
    @SerializedName("created_at_i") val created_at_i: Long?,
    @SerializedName("_tags") val _tags: List<String>?,
    @SerializedName("objectID") val objectID: String?,
    @SerializedName("_highlightResult") val _highlightResult: HighlightResultResponse?,
)