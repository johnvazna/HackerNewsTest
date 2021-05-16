package com.hackernews.app.domain.entity

import java.util.*

/** */
data class Author(
    val value: String? = null,
    val matchLevel: String? = null,
    val matchedWords: ArrayList<String>? = null,
)