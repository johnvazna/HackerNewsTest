package com.hackernews.app.data.hit

import com.hackernews.app.data.hit.data_source.local.HitDataSourceLocal
import com.hackernews.app.data.hit.data_source.remote.HitDataSourceRemote
import com.hackernews.app.domain.hit.HitRepository
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsFailure
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsResponse
import com.hackernews.app.utils.Either
import com.hackernews.app.utils.onRight
import com.hackernews.network.repository.NetworkConnectionRepository

/** */
class HitRepositoryImpl(
    private val hitHitDataSourceLocal: HitDataSourceLocal,
    private val hitHitDataSourceRemote: HitDataSourceRemote,
    networkConnectionRepository: NetworkConnectionRepository,
) : HitRepository,
    NetworkConnectionRepository by networkConnectionRepository {

    /** */
    override suspend fun getHits(): Either<GetHitsFailure, GetHitsResponse> {
        if (isOnline) {
            val remoteResponse = hitHitDataSourceRemote.getHits()
                .onRight { hitHitDataSourceLocal.saveHits(it.hits) }
            if (remoteResponse.isRight) {
                if (remoteResponse.rightValue().hits.isEmpty())
                    return remoteResponse
            }

        }
        return hitHitDataSourceLocal.getHits()
    }


}