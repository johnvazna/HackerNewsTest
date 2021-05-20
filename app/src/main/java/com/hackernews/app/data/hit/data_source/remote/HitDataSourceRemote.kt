package com.hackernews.app.data.hit.data_source.remote

import com.hackernews.app.data.hit.HitDataSource
import com.hackernews.app.data.hit.data_source.remote.model.dto.HitItemResponse
import com.hackernews.app.domain.hit.entity.Hit
import com.hackernews.app.domain.hit.uses_case.delete_hits.DeleteHitFailure
import com.hackernews.app.domain.hit.uses_case.delete_hits.DeleteHitResponse
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsFailure
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsResponse
import com.hackernews.app.utils.Either
import com.hackernews.app.utils.retrofitApiCall

class HitDataSourceRemote(
    private val hitApiService: HitService
) : HitDataSource {

    /** */
    override suspend fun getHits(): Either<GetHitsFailure, GetHitsResponse> {
        return try {
            val response = retrofitApiCall { hitApiService.getHits() }
            val hits = GetHitsResponse(response.hits.map(::toHit))
            Either.Right(hits)

        } catch (e: Exception) {
            val message: String = e.message ?: e.javaClass.simpleName
            val failure = GetHitsFailure.DetailsFailure(message)
            Either.Left(failure)
        }
    }

    /** */
    override suspend fun deleteHit(hit: Hit): Either<DeleteHitFailure, DeleteHitResponse> {
        TODO()
    }

    private fun toHit(hitItemResponse: HitItemResponse): Hit {
        return Hit(
            hitItemResponse.created_at,
            hitItemResponse.title,
            hitItemResponse.url,
            hitItemResponse.author,
            hitItemResponse.points,
            hitItemResponse.story_text,
            hitItemResponse.comment_text,
            hitItemResponse.num_comments,
            hitItemResponse.story_id,
            hitItemResponse.story_title,
            hitItemResponse.story_url,
            hitItemResponse.parent_id,
            hitItemResponse.created_at_i,
            hitItemResponse.objectID,
        )
    }

}