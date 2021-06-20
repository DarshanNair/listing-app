package com.target.targetcasestudy.dealslist.repository.injection

import com.target.targetcasestudy.dealslist.repository.LoadDealsRepository
import com.target.targetcasestudy.dealslist.repository.LoadDealsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class LoadDealsRepositoryModule {

    @Provides
    fun provideLoadDealsRepository(repository: LoadDealsRepositoryImpl): LoadDealsRepository =
        repository

}