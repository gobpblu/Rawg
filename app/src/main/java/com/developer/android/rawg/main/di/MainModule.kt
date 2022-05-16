package com.developer.android.rawg.main.di

import com.developer.android.rawg.main.interactor.MainInteractor
import com.developer.android.rawg.main.repository.MainRemoteRepository
import com.developer.android.rawg.main.ui.differenttypes.DifferentTypesContract
import com.developer.android.rawg.main.ui.differenttypes.DifferentTypesPresenter
import com.developer.android.rawg.main.ui.main.MainContract
import com.developer.android.rawg.main.ui.main.MainPresenter
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object MainModule {
    fun create() = module {
        singleOf(::MainPresenter) bind MainContract.Presenter::class
        factoryOf(::MainInteractor)
        singleOf(::MainRemoteRepository)
        singleOf(::DifferentTypesPresenter) bind DifferentTypesContract.Presenter::class

    }
}