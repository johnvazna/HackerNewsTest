package com.hackernews.app.di.feature.hit

import com.hackernews.app.data.remote.services.HitService
import com.hackernews.app.domain.hit.HitRepository
import com.hackernews.app.domain.hit.HitRepositoryImpl
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsUseCase
import com.hackernews.app.presentation.main.hit.HitViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

/* */
val hitModule: Module = module {

    /* */
    viewModel { HitViewModel(getHitsUseCase = get()) }

    /** USE CASE **/
    factory { GetHitsUseCase(hitRepository = get()) }

    /** REPOSITORY **/
    single<HitRepository> {
        HitRepositoryImpl(
            context = androidContext(),
            hitDao = get(),
            hitService = get(),
        )
    }

    /** API SERVICE **/
    single<HitService> { get<Retrofit>().create(HitService::class.java) }

}