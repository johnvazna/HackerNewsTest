package com.hackernews.app.domain.hit.uses_case.get_hits

/** */
sealed class GetHitsFailure {

    /** */
    data class DetailsFailure(
        val details: String,
    ) : GetHitsFailure()
}