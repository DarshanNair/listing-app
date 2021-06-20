package com.target.targetcasestudy.dealslist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.dealsdetails.view.DealItemFragment
import com.target.targetcasestudy.dealslist.view.adapter.DealItemAdapter
import com.target.targetcasestudy.dealslist.viewmodel.DealsListViewModel
import com.target.targetcasestudy.dealslist.viewmodel.DealsListViewModel.State
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_deal_list.*
import kotlinx.android.synthetic.main.view_deal_list_loaded.*
import javax.inject.Inject


class DealsListFragment : Fragment() {

    enum class UIState {
        LOADING,
        LOADED
    }

    @Inject
    lateinit var dealsListViewModel: DealsListViewModel

    @Inject
    lateinit var dealItemAdapter: DealItemAdapter

    @Inject
    lateinit var dealItemLayoutManager: RecyclerView.LayoutManager

    @Inject
    lateinit var dividerItemDecoration: DividerItemDecoration

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        AndroidSupportInjection.inject(this)
        return inflater.inflate(R.layout.fragment_deal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        dealsListViewModel.apply {
            state().observe(viewLifecycleOwner, { it?.let { onDealsLoaded(it) } })
            loadDeals()
        }
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = dealItemLayoutManager
        recycler_view.adapter = dealItemAdapter
        recycler_view.addItemDecoration(dividerItemDecoration)
        dealItemAdapter.setOnClickListener {
            requireActivity().supportFragmentManager.commit {
                addToBackStack(null)
                add(R.id.container, DealItemFragment.newInstance(it.id))
            }
        }
    }

    private fun onDealsLoaded(state: State) {
        when (state) {
            State.Loading -> {
                view_flipper_deal_item.displayedChild = UIState.LOADING.ordinal
            }
            is State.Success -> {
                view_flipper_deal_item.displayedChild = UIState.LOADED.ordinal
                dealItemAdapter.setDealItems(state.topTrendingUsers)
            }
            State.Empty -> {
                //TODO
            }
            State.Error -> {
                //TODO
            }
        }
    }

}
