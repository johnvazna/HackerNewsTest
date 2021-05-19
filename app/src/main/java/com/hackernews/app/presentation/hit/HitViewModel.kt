package com.hackernews.app.presentation.hit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
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
): ViewModel() {

    /** */
    fun getHitsAsLiveData(): LiveData<GetHitsStatus> = flow<GetHitsStatus> {
        getHitsUseCase.run()
            .onLeft { emit(Status.Failed(it)) }
            .onRight { emit(Status.Done(it)) }
    }.asLiveData(Dispatchers.IO)

}