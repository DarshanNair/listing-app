package com.target.targetcasestudy.dealslist.domain.injection

import com.target.targetcasestudy.dealslist.domain.LoadDealsUseCase
import com.target.targetcasestudy.dealslist.domain.LoadDealsUseCaseImpl
import com.target.targetcasestudy.dealslist.repository.injection.LoadDealsRepositoryModule
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [LoadDealsRepositoryModule::class])
class LoadDealsUseCaseModule {

    @Provides
    fun provideLoadDealsUseCase(usecase: LoadDealsUseCaseImpl): LoadDealsUseCase =
        usecase

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

}