package com.hackernews.app.domain.hit.uses_case.get_hits

/** */
sealed class GetHitsFailure {

    /** */
    object NetworkConnectionFailure : GetHitsFailure()

    /** */
    data class DetailsFailure(
        val details: String,
    ) : GetHitsFailure()
}