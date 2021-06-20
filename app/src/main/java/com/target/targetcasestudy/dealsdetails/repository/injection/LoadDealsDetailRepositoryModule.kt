package com.target.targetcasestudy.dealsdetails.repository.injection

import com.target.targetcasestudy.dealsdetails.repository.LoadDealsDetailRepository
import com.target.targetcasestudy.dealsdetails.repository.LoadDealsDetailRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class LoadDealsDetailRepositoryModule {

    @Provides
    fun provideLoadDealsDetailRepository(repository: LoadDealsDetailRepositoryImpl): LoadDealsDetailRepository =
        repository

}