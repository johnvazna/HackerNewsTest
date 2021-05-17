package com.hackernews.app.domain.hit.uses_case.get_hits

import com.hackernews.app.domain.entity.Hit

/** */
data class GetHitsResponse(
    val hits: List<Hit>
)