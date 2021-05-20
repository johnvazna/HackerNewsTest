package com.hackernews.app.data.hit

import com.hackernews.app.domain.hit.entity.Hit
import com.hackernews.app.domain.hit.uses_case.delete_hits.DeleteHitFailure
import com.hackernews.app.domain.hit.uses_case.delete_hits.DeleteHitResponse
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsFailure
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsResponse
import com.hackernews.app.utils.Either

internal interface HitDataSource {

    /** */
    suspend fun getHits(): Either<GetHitsFailure, GetHitsResponse>

    /** */
    suspend fun deleteHit(hit: Hit): Either<DeleteHitFailure, DeleteHitResponse>

}