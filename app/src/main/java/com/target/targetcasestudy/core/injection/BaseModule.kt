package com.target.targetcasestudy.core.injection

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.bumptech.glide.Glide
import com.target.targetcasestudy.BuildConfig
import com.target.targetcasestudy.core.imageloader.ImageLoader
import com.target.targetcasestudy.core.injection.qualifiers.ForApplication
import com.target.targetcasestudy.core.injection.qualifiers.ForIoThread
import com.target.targetcasestudy.core.injection.qualifiers.ForMainThread
import com.target.targetcasestudy.core.injection.scopes.PerApplication
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class BaseModule {

    @Provides
    @ForApplication
    fun provideContext(application: Application): Context = application

    @Provides
    @ForApplication
    fun provideApplication(application: Application): Application = application

    @Provides
    @PerApplication
    @ForIoThread
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @PerApplication
    @ForMainThread
    fun provideMainScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @PerApplication
    fun provideImageLoader(@ForApplication context: Context) = ImageLoader(Glide.with(context))

    @Provides
    @PerApplication
    fun provideSharedPreferences(application: Application): SharedPreferences =
        application.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)

}
