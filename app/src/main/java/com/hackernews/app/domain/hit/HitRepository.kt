package com.hackernews.app.domain.hit

import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsFailure
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsResponse
import com.hackernews.app.utils.Either

/** */
interface HitRepository {

    /** */
    suspend fun getHits(): Either<GetHitsFailure, GetHitsResponse>

}