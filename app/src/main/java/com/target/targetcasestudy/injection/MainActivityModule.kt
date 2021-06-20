package com.target.targetcasestudy.injection

import android.content.Context
import com.target.targetcasestudy.MainActivity
import com.target.targetcasestudy.core.injection.scopes.PerActivity
import com.target.targetcasestudy.dealsdetails.injection.DealsDetailsFragmentBuilderModule
import com.target.targetcasestudy.dealslist.injection.DealsListFragmentBuilderModule
import com.target.targetcasestudy.payment.injection.PaymentDialogFragmentBuilderModule
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        DealsListFragmentBuilderModule::class,
        DealsDetailsFragmentBuilderModule::class,
        PaymentDialogFragmentBuilderModule::class
    ]
)
class MainActivityModule {

    @Provides
    @PerActivity
    fun provideContext(activity: MainActivity): Context = activity

}