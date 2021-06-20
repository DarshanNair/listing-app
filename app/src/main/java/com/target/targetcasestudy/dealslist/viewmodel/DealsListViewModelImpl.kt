package com.target.targetcasestudy.dealslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.target.targetcasestudy.dealslist.domain.LoadDealsUseCase
import com.target.targetcasestudy.dealslist.model.DealItem
import javax.inject.Inject

class DealsListViewModelImpl @Inject internal constructor(
    private val loadDealsUseCase: LoadDealsUseCase
) : DealsListViewModel() {

    private val stateLiveData = MediatorLiveData<State>()

    init {
        loadDealsUseCase.setCallback(this)
    }

    override fun state(): LiveData<State> = stateLiveData

    override fun loadDeals() {
        stateLiveData.value = State.Loading
        loadDealsUseCase.execute()
    }

    override fun onDealsFetchSuccess(dealItems: List<DealItem>) {
        if (dealItems.isEmpty()) {
            stateLiveData.value = State.Empty
        } else {
            stateLiveData.value = State.Success(dealItems)
        }
    }

    override fun onDealsFetchError(throwable: Throwable) {
        stateLiveData.value = State.Error
    }

    public override fun onCleared() {
        super.onCleared()
        loadDealsUseCase.cleanup()
    }

}
