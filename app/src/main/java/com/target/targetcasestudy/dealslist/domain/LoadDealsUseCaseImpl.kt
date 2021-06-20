package com.target.targetcasestudy.dealslist.domain

import com.target.targetcasestudy.core.domain.BaseUseCase
import com.target.targetcasestudy.core.injection.qualifiers.ForIoThread
import com.target.targetcasestudy.core.injection.qualifiers.ForMainThread
import com.target.targetcasestudy.core.network.model.Deals
import com.target.targetcasestudy.dealslist.domain.LoadDealsUseCase.Callback
import com.target.targetcasestudy.dealslist.model.DealItem
import com.target.targetcasestudy.dealslist.repository.LoadDealsRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoadDealsUseCaseImpl @Inject constructor(
    compositeDisposable: CompositeDisposable,
    private val loadDealsRepository: LoadDealsRepository,
    @ForIoThread private val ioScheduler: Scheduler,
    @ForMainThread private val mainScheduler: Scheduler
) : BaseUseCase(compositeDisposable), LoadDealsUseCase {

    private var callback: Callback? = null

    override fun execute() {
        trackDisposable(
            loadDealsRepository.getDeals()
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

    private fun onSuccess(deals: List<DealItem>) {
        callback?.onDealsFetchSuccess(deals)
    }

    private fun onError(throwable: Throwable) {
        callback?.onDealsFetchError(throwable)
    }

    private fun map(deals: Deals.Products): List<DealItem> {
        val dealItems = mutableListOf<DealItem>()
        deals.products().forEach {
            dealItems.add(
                DealItem(
                    id = it.id(),
                    title = it.title(),
                    description = it.description(),
                    price = it.regularPrice().displayString(),
                    aisle = it.aisle(),
                    url = it.imageUrl()
                )
            )
        }
        return dealItems
    }

}
