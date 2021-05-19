package com.hackernews.network

import com.hackernews.network.repository.NetworkConnectionRepository
import com.hackernews.network.repository.NetworkConnectionRepositoryImpl
import com.hackernews.network.repository.data.NetworkConnectionApiService
import com.hackernews.network.repository.data.NetworkConnectionRetrofitBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

/* */
val networkModule: Module = module {

    /* */
    single<NetworkConnectionRepository>(createdAtStart = true) {
        NetworkConnectionRepositoryImpl()
    }

    /* */
    single<NetworkConnectionApiService> {
        NetworkConnectionRetrofitBuilder()
            .build()
            .create(NetworkConnectionApiService::class.java)
    }

}