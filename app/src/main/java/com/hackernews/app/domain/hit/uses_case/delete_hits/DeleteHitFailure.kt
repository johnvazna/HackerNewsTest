package com.hackernews.app.domain.hit.uses_case.delete_hits

/** */
sealed class DeleteHitFailure {

    /** */
    data class DetailsFailure(
        val details: String,
    ) : DeleteHitFailure()
}