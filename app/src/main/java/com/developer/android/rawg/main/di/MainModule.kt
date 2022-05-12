package com.developer.android.rawg.main.di

import com.developer.android.rawg.main.interactor.MainInteractor
import com.developer.android.rawg.main.repository.MainRemoteRepository
import com.developer.android.rawg.main.ui.MainContract
import com.developer.android.rawg.main.ui.MainPresenter
import org.koin.dsl.bind
import org.koin.dsl.module

object MainModule {
    fun create() = module {
        single { MainPresenter(get()) } bind MainContract.Presenter::class
        factory { MainInteractor(get()) } bind MainInteractor::class
        single { MainRemoteRepository(get()) }
    }
}