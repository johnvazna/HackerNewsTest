package com.hackernews.app.data.hit.data_source.remote.model.dto

import com.google.gson.annotations.SerializedName

data class HighlightResultResponse(
    @SerializedName("title") val title: TitleResponse?,
    @SerializedName("url") val url: UrlResponse?,
    @SerializedName("author") val author: AuthorResponse?,
    @SerializedName("comment_text") val comment_text: CommentTextResponse?,
    @SerializedName("story_title") val story_title: StoryTitleResponse?,
    @SerializedName("story_url") val story_url: StoryUrlResponse?,
    @SerializedName("story_text") val story_text: StoryTextResponse?,
)