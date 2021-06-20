package com.target.targetcasestudy.dealsdetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.target.targetcasestudy.R
import com.target.targetcasestudy.core.imageloader.ImageLoader
import com.target.targetcasestudy.dealsdetails.model.DealDetail
import com.target.targetcasestudy.dealsdetails.viewmodel.DealsDetailViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_deal_item.*
import kotlinx.android.synthetic.main.view_deal_details_loaded.*
import javax.inject.Inject


class DealItemFragment : Fragment() {

    enum class UIState {
        LOADING,
        LOADED
    }

    @Inject
    lateinit var dealsDetailViewModel: DealsDetailViewModel

    @Inject
    lateinit var imageLoader: ImageLoader

    companion object {
        private const val KEY_DEAL_ID = "KEY_DEAL_ID"
        fun newInstance(id: Int) = DealItemFragment().apply {
            arguments = Bundle().apply { putInt(KEY_DEAL_ID, id) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.fragment_deal_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dealsDetailViewModel.apply {
            state().observe(viewLifecycleOwner, { it?.let { onDealsDetailsLoaded(it) } })
            arguments?.getInt(KEY_DEAL_ID)?.let {
                loadDealsDetail(it)
            }
        }
    }

    private fun onDealsDetailsLoaded(state: DealsDetailViewModel.State) {
        when (state) {
            DealsDetailViewModel.State.Loading -> {
                view_flipper_deal_details.displayedChild = UIState.LOADING.ordinal
            }
            is DealsDetailViewModel.State.Success -> {
                view_flipper_deal_details.displayedChild = UIState.LOADED.ordinal
                fillContent(state.dealDetail)
            }
            DealsDetailViewModel.State.Error -> {
                //TODO
            }
        }
    }

    private fun fillContent(dealDetail: DealDetail) {
        product_details_title.text = dealDetail.title
        product_details_description.text = dealDetail.description
        product_details_price.text = dealDetail.price
        imageLoader.draw(product_details_image, dealDetail.url)
    }

}
