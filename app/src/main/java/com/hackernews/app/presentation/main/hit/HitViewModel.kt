package com.hackernews.app.presentation.main.hit

import androidx.lifecycle.ViewModel
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsUseCase

/** */
class HitViewModel(
    private val getHitsUseCase: GetHitsUseCase,
): ViewModel() {

}