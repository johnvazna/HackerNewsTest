package com.hackernews.app.di.feature.hit

import com.hackernews.app.data.ApplicationDatabase
import com.hackernews.app.data.hit.HitRepositoryImpl
import com.hackernews.app.data.hit.data_source.local.HitDataSourceLocal
import com.hackernews.app.data.hit.data_source.remote.HitDataSourceRemote
import com.hackernews.app.data.hit.data_source.remote.HitService
import com.hackernews.app.domain.hit.HitRepository
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsUseCase
import com.hackernews.app.presentation.hit.HitViewModel
import org.koin.android.ext.koin.androidApplication
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
            hitHitDataSourceLocal = get(),
            hitHitDataSourceRemote = get(),
        )
    }

    /** DATA SOURCE */
    /* LOCAL */
    single {
        HitDataSourceLocal(
            hitDao = get(),
        )
    }

    /* REMOTE */
    single {
        HitDataSourceRemote(
            hitApiService = get(),
        )
    }

    /** DAO */
    single {
        ApplicationDatabase
            .getDatabase(androidApplication())
            .hitDao()
    }

    /** API SERVICE **/
    single<HitService> {
        get<Retrofit>().create(HitService::class.java)
    }

}