package com.target.targetcasestudy.dealsdetails.injection

import com.target.targetcasestudy.core.injection.scopes.PerFragment
import com.target.targetcasestudy.dealsdetails.view.DealItemFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DealsDetailsFragmentBuilderModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [DealsDetailsFragmentModule::class])
    abstract fun bindDealItemFragment(): DealItemFragment

}