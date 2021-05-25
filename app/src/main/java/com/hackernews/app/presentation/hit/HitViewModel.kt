package com.hackernews.app.presentation.hit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.hackernews.app.domain.hit.entity.Hit
import com.hackernews.app.domain.hit.uses_case.delete_hits.DeleteHitStatus
import com.hackernews.app.domain.hit.uses_case.delete_hits.DeleteHitUseCase
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsStatus
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsUseCase
import com.hackernews.app.utils.Status
import com.hackernews.app.utils.onLeft
import com.hackernews.app.utils.onRight
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow

/** */
class HitViewModel(
    private val getHitsUseCase: GetHitsUseCase,
    private val deleteHitUseCase: DeleteHitUseCase,
): ViewModel() {

    /** */
    fun getHitsAsLiveData(): LiveData<GetHitsStatus> = flow<GetHitsStatus> {
        emit(Status.Loading())
        getHitsUseCase.run()
            .onLeft { emit(Status.Failed(it)) }
            .onRight { emit(Status.Done(it)) }
    }.asLiveData(Dispatchers.IO)

    /** */
    fun deleteHitAsLiveData(hit: Hit): LiveData<DeleteHitStatus> = flow<DeleteHitStatus> {
        deleteHitUseCase.run(hit)
            .onLeft { emit(Status.Failed(it)) }
            .onRight { emit(Status.Done(it)) }
    }.asLiveData(Dispatchers.IO)

}