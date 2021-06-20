package com.target.targetcasestudy.core.network.api

import com.target.targetcasestudy.core.network.model.Deals
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DealsApi {

    @GET("deals")
    fun getDeals(): Single<Deals.Products>

    @GET("deals/{id}")
    fun getDealsDetails(
        @Path("id") id: Int
    ): Single<Deals.Product>

}