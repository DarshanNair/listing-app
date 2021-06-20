package com.target.targetcasestudy.dealsdetails.domain.injection

import com.target.targetcasestudy.dealsdetails.domain.LoadDealsDetailUseCase
import com.target.targetcasestudy.dealsdetails.domain.LoadDealsDetailsUseCaseImpl
import com.target.targetcasestudy.dealsdetails.repository.injection.LoadDealsDetailRepositoryModule
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module(includes = [LoadDealsDetailRepositoryModule::class])
class LoadDealsDetailsUseCaseModule {

    @Provides
    fun provideLoadDealsDetailUseCase(usecase: LoadDealsDetailsUseCaseImpl): LoadDealsDetailUseCase =
        usecase

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

}