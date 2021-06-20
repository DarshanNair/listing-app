package com.target.targetcasestudy.dealslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.target.targetcasestudy.dealslist.domain.LoadDealsUseCase
import javax.inject.Inject

class DealsListViewModelFactory @Inject constructor(
    private val loadDealsUseCase: LoadDealsUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        DealsListViewModelImpl(
            loadDealsUseCase
        ) as T

}