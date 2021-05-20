package com.hackernews.app.data.hit

import com.hackernews.app.data.hit.data_source.local.HitDataSourceLocal
import com.hackernews.app.data.hit.data_source.remote.HitDataSourceRemote
import com.hackernews.app.domain.hit.HitRepository
import com.hackernews.app.domain.hit.entity.Hit
import com.hackernews.app.domain.hit.uses_case.delete_hits.DeleteHitFailure
import com.hackernews.app.domain.hit.uses_case.delete_hits.DeleteHitResponse
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsFailure
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsResponse
import com.hackernews.app.utils.Either
import com.hackernews.app.utils.onRight
import com.hackernews.network.repository.NetworkConnectionRepository

/** */
class HitRepositoryImpl(
    private val hitDataSourceLocal: HitDataSourceLocal,
    private val hitDataSourceRemote: HitDataSourceRemote,
    networkConnectionRepository: NetworkConnectionRepository,
) : HitRepository,
    NetworkConnectionRepository by networkConnectionRepository {

    /** */
    override suspend fun getHits(): Either<GetHitsFailure, GetHitsResponse> {
        if (isOnline) {
            hitDataSourceRemote.getHits().onRight { hitDataSourceLocal.saveHits(it.hits) }
        }
        return hitDataSourceLocal.getHits()
    }

    /** */
    override suspend fun deleteHit(hit: Hit): Either<DeleteHitFailure, DeleteHitResponse> {
        return hitDataSourceLocal.deleteHit(hit)
    }


}