package com.hackernews.app.data.hit.data_source.local

import com.hackernews.app.data.hit.HitDataSource
import com.hackernews.app.data.hit.data_source.local.model.HitDao
import com.hackernews.app.data.hit.data_source.local.model.HitEntity
import com.hackernews.app.domain.hit.entity.Hit
import com.hackernews.app.domain.hit.uses_case.delete_hits.DeleteHitFailure
import com.hackernews.app.domain.hit.uses_case.delete_hits.DeleteHitResponse
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
    override suspend fun deleteHit(hit: Hit): Either<DeleteHitFailure, DeleteHitResponse> {
        val hitEntity = HitEntity.fromHit(hit).apply { isDelete = true }
        hitDao.update(hitEntity)
        val response = DeleteHitResponse
        return Either.Right(response)
    }

    /** */
    suspend fun saveHits(hits: List<Hit>) {
        val hitsEntity = arrayListOf<HitEntity>()

        hits.filter { it.story_id != null }.forEach {
            val hitEntity = HitEntity.fromHit(it).apply {
                isDelete = hitDao.isHitDeleted(story_id) ?: false
            }

            hitsEntity.add(hitEntity)
        }

        hitDao.insertAll(hitsEntity)
    }

}