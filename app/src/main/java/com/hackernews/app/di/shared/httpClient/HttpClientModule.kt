package com.hackernews.app.di.shared.httpClient

import com.hackernews.app.data.RetrofitBuilder
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

/** */
val httpClientModule: Module = module {

    /* */
    single<Retrofit> { RetrofitBuilder.retrofitBase }

}