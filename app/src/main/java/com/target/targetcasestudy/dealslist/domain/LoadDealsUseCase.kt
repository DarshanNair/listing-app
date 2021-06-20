package com.target.targetcasestudy.dealslist.domain

import com.target.targetcasestudy.core.domain.UseCase
import com.target.targetcasestudy.dealslist.model.DealItem

interface LoadDealsUseCase : UseCase {

    //TODO: Pagination
    fun execute()

    fun setCallback(callback: Callback)

    interface Callback {
        fun onDealsFetchSuccess(dealItems: List<DealItem>)
        fun onDealsFetchError(throwable: Throwable)
    }

}