package com.target.targetcasestudy.dealsdetails.repository

import com.target.targetcasestudy.core.network.api.DealsApi
import com.target.targetcasestudy.core.network.model.Deals
import io.reactivex.Single
import javax.inject.Inject

class LoadDealsDetailRepositoryImpl @Inject constructor(
    private val dealsApi: DealsApi
) : LoadDealsDetailRepository {

    override fun getDealsDetails(id: Int): Single<Deals.Product> = dealsApi.getDealsDetails(id)

}