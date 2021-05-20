package com.hackernews.app.domain.hit.entity

import java.io.Serializable

data class Hit(
    var created_at: String?,
    var title: String?,
    val url: String?,
    val author: String?,
    val points: Int?,
    val story_text: String?,
    val comment_text: String?,
    val num_comments: Int?,
    val story_id: Long?,
    val story_title: String?,
    val story_url: String?,
    val parent_id: Long?,
    val created_at_i: Long?,
    val objectID: String?,
): Serializable