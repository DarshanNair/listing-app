package com.target.targetcasestudy.core.injection

import android.app.Application
import com.target.targetcasestudy.core.DealsApplication
import com.target.targetcasestudy.core.injection.scopes.PerApplication
import com.target.targetcasestudy.core.network.api.injection.DealsApiModule
import com.target.targetcasestudy.injection.MainActivityBuilderModule
import dagger.BindsInstance
import dagger.Component

@PerApplication
@Component(
    modules = [
        BaseModule::class,
        DealsApiModule::class,
        MainActivityBuilderModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(application: DealsApplication)

}