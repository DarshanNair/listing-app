package com.target.targetcasestudy.dealslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.target.targetcasestudy.dealslist.domain.LoadDealsUseCase
import com.target.targetcasestudy.dealslist.model.DealItem

abstract class DealsListViewModel : ViewModel(), LoadDealsUseCase.Callback {

    sealed class State {
        object Loading : State()
        data class Success(val topTrendingUsers: List<DealItem>) : State()
        object Empty : State()
        object Error : State()
    }

    abstract fun state(): LiveData<State>

    abstract fun loadDeals()

}
