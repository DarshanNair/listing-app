package com.target.targetcasestudy.dealsdetails.domain

import com.target.targetcasestudy.core.domain.BaseUseCase
import com.target.targetcasestudy.core.injection.qualifiers.ForIoThread
import com.target.targetcasestudy.core.injection.qualifiers.ForMainThread
import com.target.targetcasestudy.core.network.model.Deals
import com.target.targetcasestudy.dealsdetails.domain.LoadDealsDetailUseCase.Callback
import com.target.targetcasestudy.dealsdetails.model.DealDetail
import com.target.targetcasestudy.dealsdetails.repository.LoadDealsDetailRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoadDealsDetailsUseCaseImpl @Inject constructor(
    compositeDisposable: CompositeDisposable,
    private val loadDealsDetailRepository: LoadDealsDetailRepository,
    @ForIoThread private val ioScheduler: Scheduler,
    @ForMainThread private val mainScheduler: Scheduler
) : BaseUseCase(compositeDisposable), LoadDealsDetailUseCase {

    private var callback: Callback? = null

    override fun execute(id: Int) {
        trackDisposable(
            loadDealsDetailRepository.getDealsDetails(id)
                .map { map(it) }
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(::onSuccess, ::onError)
        )
    }

    override fun setCallback(callback: Callback) {
        this.callback = callback
    }

    override fun cleanup() {
        callback = null
        super.cleanup()
    }

    private fun onSuccess(dealDetail: DealDetail) {
        callback?.onDealDetailsFetchSuccess(dealDetail)
    }

    private fun onError(throwable: Throwable) {
        callback?.onDealDetailsFetchError(throwable)
    }

    private fun map(deals: Deals.Product): DealDetail {
        return DealDetail(
            id = deals.id(),
            title = deals.title(),
            description = deals.description(),
            price = deals.regularPrice().displayString(),
            url = deals.imageUrl()
        )
    }

}
