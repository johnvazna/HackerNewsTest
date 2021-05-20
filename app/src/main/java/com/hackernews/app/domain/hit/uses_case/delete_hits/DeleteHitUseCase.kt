package com.hackernews.app.domain.hit.uses_case.delete_hits

import com.hackernews.app.domain.hit.HitRepository
import com.hackernews.app.domain.hit.entity.Hit
import com.hackernews.app.utils.Either

/** */
class DeleteHitUseCase(private val hitRepository: HitRepository) {
    /** */
    suspend fun run(hit: Hit): Either<DeleteHitFailure, DeleteHitResponse> =
        hitRepository.deleteHit(hit)
}