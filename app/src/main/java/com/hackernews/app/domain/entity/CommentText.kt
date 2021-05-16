package com.hackernews.app.domain.entity

import java.util.*

/** */
data class CommentText(
    val value: String? = null,
    val matchLevel: String? = null,
    val fullyHighlighted: Boolean? = null,
    val matchedWords: ArrayList<String>
)