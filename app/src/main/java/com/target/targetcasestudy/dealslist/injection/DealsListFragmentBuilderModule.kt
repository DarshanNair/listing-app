package com.target.targetcasestudy.dealslist.injection

import com.target.targetcasestudy.core.injection.scopes.PerFragment
import com.target.targetcasestudy.dealslist.view.DealsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DealsListFragmentBuilderModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [DealsListFragmentModule::class])
    abstract fun bindDealsListFragment(): DealsListFragment

}