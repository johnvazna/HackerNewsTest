package com.hackernews.app.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/** */
@Entity(tableName = "hints")
data class Hit(
    val created_at: String,
    val title: String? = null,
    val url: String? = null,
    val author: String? = null,
    val points: Long? = null,
    val story_text: String? = null,
    val comment_text: String? = null,
    val num_comments: Long? = null,
    @PrimaryKey
    val story_id: Long? = null,
    val story_title: String? = null,
    val story_url: String? = null,
    val parent_id: Long? = null,
    val created_at_i: Long? = null,
    //val _tags: ArrayList<String>? = null,
    val objectID: String? = null,
)