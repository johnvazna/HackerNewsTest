package com.hackernews.app.data.hit.data_source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hackernews.app.domain.hit.entity.Hit

/** */
@Entity(tableName = "hits")
data class HitEntity(
    @ColumnInfo(name = "created_at") var created_at: String?,
    @ColumnInfo(name = "title") var title: String?,
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
    @ColumnInfo(name = "isDelete") var isDelete: Boolean,
) {

    /** */
    fun toHit(): Hit =
        Hit(
            created_at,
            title,
            url,
            author,
            points,
            story_text,
            comment_text,
            num_comments,
            story_id,
            story_title,
            story_url,
            parent_id,
            created_at_i,
            objectID,
        )

    /** */
    companion object {

        /** */
        fun fromHit(hit: Hit): HitEntity =
            HitEntity(
                hit.created_at,
                hit.title,
                hit.url,
                hit.author,
                hit.points,
                hit.story_text,
                hit.comment_text,
                hit.num_comments,
                hit.story_id!!,
                hit.story_title,
                hit.story_url,
                hit.parent_id,
                hit.created_at_i,
                hit.objectID,
                false
            )

    }

}