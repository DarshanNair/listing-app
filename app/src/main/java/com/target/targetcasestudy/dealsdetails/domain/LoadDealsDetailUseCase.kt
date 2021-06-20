package com.target.targetcasestudy.dealsdetails.domain

import com.target.targetcasestudy.core.domain.UseCase
import com.target.targetcasestudy.dealsdetails.model.DealDetail

interface LoadDealsDetailUseCase : UseCase {

    fun execute(id: Int)

    fun setCallback(callback: Callback)

    interface Callback {
        fun onDealDetailsFetchSuccess(dealDetail: DealDetail)
        fun onDealDetailsFetchError(throwable: Throwable)
    }

}