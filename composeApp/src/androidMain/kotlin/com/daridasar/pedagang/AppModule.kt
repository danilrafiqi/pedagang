package com.daridasar.pedagang

import com.daridasar.pedagang.network.ProductApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ProductApi> { ProductApi() }
    single<ProductSDK> {
        ProductSDK(
            databaseDriverFactory = AndroidDatabaseDriverFactory(
                androidContext()
            ), api = get()
        )
    }
    viewModel { ProductViewModel(sdk = get()) }

}