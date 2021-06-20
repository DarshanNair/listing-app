package com.target.targetcasestudy.dealsdetails.injection

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.target.targetcasestudy.core.injection.qualifiers.ForFragment
import com.target.targetcasestudy.core.injection.scopes.PerFragment
import com.target.targetcasestudy.dealsdetails.domain.injection.LoadDealsDetailsUseCaseModule
import com.target.targetcasestudy.dealsdetails.view.DealItemFragment
import com.target.targetcasestudy.dealsdetails.viewmodel.DealsDetailViewModel
import com.target.targetcasestudy.dealsdetails.viewmodel.DealsDetailViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [LoadDealsDetailsUseCaseModule::class])
class DealsDetailsFragmentModule {

    @Provides
    @PerFragment
    @ForFragment
    internal fun provideContext(fragment: DealItemFragment): Context = fragment.requireContext()

    @Provides
    @PerFragment
    fun provideDealsListViewModel(
        fragment: DealItemFragment,
        factory: DealsDetailViewModelFactory
    ): DealsDetailViewModel =
        ViewModelProvider(fragment, factory).get(DealsDetailViewModel::class.java)

}