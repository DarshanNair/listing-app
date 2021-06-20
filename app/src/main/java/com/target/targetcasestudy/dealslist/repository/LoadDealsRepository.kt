package com.target.targetcasestudy.dealslist.repository

import com.target.targetcasestudy.core.network.model.Deals
import io.reactivex.Single

interface LoadDealsRepository {

    fun getDeals(): Single<Deals.Products>

}