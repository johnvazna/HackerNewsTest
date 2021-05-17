package com.hackernews.app.domain.hit.uses_case.get_hits

import com.hackernews.app.domain.hit.HitRepository
import com.hackernews.app.utils.Either

/** */
class GetHitsUseCase(private val hitRepository: HitRepository) {
    /** */
    suspend fun run(): Either<GetHitsFailure, GetHitsResponse> = hitRepository.getHits()
}