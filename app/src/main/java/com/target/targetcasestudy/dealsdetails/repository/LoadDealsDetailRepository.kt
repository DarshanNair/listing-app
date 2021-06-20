package com.target.targetcasestudy.dealsdetails.repository

import com.target.targetcasestudy.core.network.model.Deals
import io.reactivex.Single

interface LoadDealsDetailRepository {

    fun getDealsDetails(id: Int): Single<Deals.Product>

}