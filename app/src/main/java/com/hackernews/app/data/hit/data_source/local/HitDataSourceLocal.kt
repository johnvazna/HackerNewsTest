package com.hackernews.app.data.hit.data_source.local

import com.hackernews.app.data.hit.HitDataSource
import com.hackernews.app.data.hit.data_source.local.model.HitDao
import com.hackernews.app.data.hit.data_source.local.model.HitEntity
import com.hackernews.app.domain.hit.entity.Hit
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsFailure
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsResponse
import com.hackernews.app.utils.Either

class HitDataSourceLocal(
    private val hitDao: HitDao
) : HitDataSource {

    /** */
    override suspend fun getHits(): Either<GetHitsFailure, GetHitsResponse> {
        val hits = hitDao.getHits().map(HitEntity::toHit)
        val response = GetHitsResponse(hits)
        return Either.Right(response)
    }

    /** */
    suspend fun saveHits(hits: List<Hit>) {
        val hitsEntity = hits.map(HitEntity.Companion::fromHit)
        hitDao.insertAll(hitsEntity)
    }

}