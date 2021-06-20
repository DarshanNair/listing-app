package com.target.targetcasestudy.dealsdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.dealsdetails.domain.LoadDealsDetailUseCase
import com.target.targetcasestudy.dealsdetails.model.DealDetail

abstract class DealsDetailViewModel : ViewModel(), LoadDealsDetailUseCase.Callback {

    sealed class State {
        object Loading : State()
        data class Success(val dealDetail: DealDetail) : State()
        object Error : State()
    }

    abstract fun state(): LiveData<State>

    abstract fun loadDealsDetail(id: Int)

}
