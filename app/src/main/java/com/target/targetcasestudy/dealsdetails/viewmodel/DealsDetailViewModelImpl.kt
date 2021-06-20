package com.target.targetcasestudy.dealsdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.target.targetcasestudy.dealsdetails.domain.LoadDealsDetailUseCase
import com.target.targetcasestudy.dealsdetails.model.DealDetail
import javax.inject.Inject

class DealsDetailViewModelImpl @Inject internal constructor(
    private val loadDealsDetailUseCase: LoadDealsDetailUseCase
) : DealsDetailViewModel() {

    private val stateLiveData = MediatorLiveData<State>()

    init {
        loadDealsDetailUseCase.setCallback(this)
    }

    override fun state(): LiveData<State> = stateLiveData

    override fun loadDealsDetail(id: Int) {
        stateLiveData.value = State.Loading
        loadDealsDetailUseCase.execute(id)
    }

    override fun onDealDetailsFetchSuccess(dealDetail: DealDetail) {
        stateLiveData.value = State.Success(dealDetail)
    }

    override fun onDealDetailsFetchError(throwable: Throwable) {
        stateLiveData.value = State.Error
    }

    public override fun onCleared() {
        super.onCleared()
        loadDealsDetailUseCase.cleanup()
    }

}
