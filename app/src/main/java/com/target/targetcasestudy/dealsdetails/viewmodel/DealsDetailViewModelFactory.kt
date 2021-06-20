package com.target.targetcasestudy.dealsdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.target.targetcasestudy.dealsdetails.domain.LoadDealsDetailUseCase
import javax.inject.Inject

class DealsDetailViewModelFactory @Inject constructor(
    private val loadDealsDetailUseCase: LoadDealsDetailUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        DealsDetailViewModelImpl(
            loadDealsDetailUseCase
        ) as T

}