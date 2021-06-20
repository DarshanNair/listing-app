package com.target.targetcasestudy.dealslist.repository

import com.target.targetcasestudy.core.network.api.DealsApi
import com.target.targetcasestudy.core.network.model.Deals
import io.reactivex.Single
import javax.inject.Inject

class LoadDealsRepositoryImpl @Inject constructor(
    private val dealsApi: DealsApi
) : LoadDealsRepository {

    override fun getDeals(): Single<Deals.Products> = dealsApi.getDeals()

}