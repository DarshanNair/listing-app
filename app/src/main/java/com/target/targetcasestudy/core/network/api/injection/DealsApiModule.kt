package com.target.targetcasestudy.core.network.api.injection

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.target.targetcasestudy.core.injection.qualifiers.ForApplication
import com.target.targetcasestudy.core.injection.scopes.PerApplication
import com.target.targetcasestudy.core.network.api.AutoValueTypeAdapterFactory
import com.target.targetcasestudy.core.network.api.DealsApi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class DealsApiModule {

    companion object {
        private const val CACHE_SIZE = 50 * 1024 * 1024
    }

    @Provides
    @PerApplication
    fun provideOkHttpClient(okHttpBuilder: OkHttpClient.Builder): OkHttpClient {
        return okHttpBuilder.build()
    }

    @Provides
    @PerApplication
    fun provideMangoApi(
        retrofitBuilder: Retrofit.Builder,
        client: OkHttpClient,
        gson: Gson
    ): DealsApi {
        return retrofitBuilder.client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://api.target.com/mobile_case_study_deals/v1/")
            .build()
            .create(DealsApi::class.java)
    }

    @Provides
    @PerApplication
    fun provideMangoApiGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapterFactory(AutoValueTypeAdapterFactory.create())
            .create()
    }

    @Provides
    @PerApplication
    fun provideOkHttpClientBuilder(cache: Cache): OkHttpClient.Builder {
        return OkHttpClient().newBuilder()
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .cache(cache)
            .connectTimeout(10L, TimeUnit.SECONDS)
            .readTimeout(30L, TimeUnit.SECONDS)
    }

    @Provides
    @PerApplication
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Provides
    @PerApplication
    fun provideCache(@ForApplication context: Context): Cache =
        Cache(context.cacheDir, CACHE_SIZE.toLong())

}
