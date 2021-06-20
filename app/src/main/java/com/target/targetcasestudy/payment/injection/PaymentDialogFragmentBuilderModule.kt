package com.target.targetcasestudy.payment.injection

import com.target.targetcasestudy.core.injection.scopes.PerFragment
import com.target.targetcasestudy.payment.view.PaymentDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PaymentDialogFragmentBuilderModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindPaymentDialogFragment(): PaymentDialogFragment

}