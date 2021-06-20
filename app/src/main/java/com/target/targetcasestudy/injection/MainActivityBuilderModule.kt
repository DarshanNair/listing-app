package com.target.targetcasestudy.injection

import com.target.targetcasestudy.MainActivity
import com.target.targetcasestudy.core.injection.scopes.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBuilderModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivityActivity(): MainActivity

}