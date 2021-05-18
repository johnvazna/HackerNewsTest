package com.hackernews.app.domain.hit

import android.content.Context
import com.hackernews.app.data.local.dao.HitDao
import com.hackernews.app.data.local.entity.Hit
import com.hackernews.app.data.remote.services.HitService
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsFailure
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsResponse
import com.hackernews.app.utils.Either
import com.hackernews.app.utils.baseDataSource
import com.hackernews.app.utils.messageOrClassName

class HitRepositoryImpl(
    private val context: Context,
    private val hitDao: HitDao,
    private val hitService: HitService,
) : HitRepository {

    /** */
    override suspend fun getHits(): Either<GetHitsFailure, GetHitsResponse> {
        return try {
            val responseService = baseDataSource { hitService.getHits() }
            responseService.hits.forEach { hit ->
                if (hitDao.getHitById(hit.story_id) != null) {
                    with(hitDao) {
                        insert(
                            Hit(
                                hit.created_at,
                                hit.title,
                                hit.url,
                                hit.author,
                                hit.points,
                                hit.story_text,
                                hit.comment_text,
                                hit.num_comments,
                                hit.story_id,
                                hit.story_title,
                                hit.story_url,
                                hit.parent_id,
                                hit.created_at_i,
                                hit.objectID,
                            )
                        )
                    }
                }
            }

            val hits = hitDao.getHits()
            val response = GetHitsResponse(hits)
            Either.Right(response)

        } catch (exception: Exception) {
            val failure = when {
                else -> GetHitsFailure.DetailsFailure(exception.messageOrClassName())
            }
            Either.Left(failure)
        }
    }
}