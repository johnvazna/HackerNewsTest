package com.hackernews.app.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** */
@Entity(tableName = "hits")
data class Hit(
    @ColumnInfo(name = "created_at") val created_at: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "author") val author: String?,
    @ColumnInfo(name = "points") val points: Int?,
    @ColumnInfo(name = "story_text") val story_text: String?,
    @ColumnInfo(name = "comment_text") val comment_text: String?,
    @ColumnInfo(name = "num_comments") val num_comments: Int?,
    @PrimaryKey
    @ColumnInfo(name = "story_id") val story_id: Long,
    @ColumnInfo(name = "story_title") val story_title: String?,
    @ColumnInfo(name = "story_url") val story_url: String?,
    @ColumnInfo(name = "parent_id") val parent_id: Long?,
    @ColumnInfo(name = "created_at_i") val created_at_i: Long?,
    @ColumnInfo(name = "objectID") val objectID: String?,
)