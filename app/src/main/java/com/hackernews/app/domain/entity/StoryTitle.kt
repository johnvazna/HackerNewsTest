package com.hackernews.app.domain.entity

import java.util.*

/** */
class StoryTitle(
    val value: String? = null,
    val matchLevel: String? = null,
    val matchedWords: ArrayList<String>,
)