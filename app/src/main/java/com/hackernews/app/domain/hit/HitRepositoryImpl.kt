package com.hackernews.app.domain.hit

import android.content.Context
import com.hackernews.app.data.local.dao.HitDao
import com.hackernews.app.data.remote.services.HitService
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsFailure
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsResponse
import com.hackernews.app.utils.Either
import com.hackernews.app.utils.baseDataSource
import com.hackernews.app.utils.messageOrClassName
import java.net.ConnectException

class HitRepositoryImpl(
    private val context: Context,
    private val hitDao: HitDao,
    private val hitService: HitService,
) : HitRepository {

    /** */
    override suspend fun getHits(): Either<GetHitsFailure, GetHitsResponse> {
        return try {
            val response = baseDataSource { hitService.getHits() }
            return Either.Right(GetHitsResponse(response))

        } catch (exception: Exception) {
            val failure = when (exception) {
                is ConnectException -> GetHitsFailure.NetworkConnectionFailure
                else -> GetHitsFailure.DetailsFailure(exception.messageOrClassName())
            }
            Either.Left(failure)
        }
    }
}