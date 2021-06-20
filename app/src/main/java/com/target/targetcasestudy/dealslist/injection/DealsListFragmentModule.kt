package com.target.targetcasestudy.dealslist.injection

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.core.injection.qualifiers.ForFragment
import com.target.targetcasestudy.core.injection.scopes.PerFragment
import com.target.targetcasestudy.dealslist.domain.injection.LoadDealsUseCaseModule
import com.target.targetcasestudy.dealslist.view.DealsListFragment
import com.target.targetcasestudy.dealslist.viewmodel.DealsListViewModel
import com.target.targetcasestudy.dealslist.viewmodel.DealsListViewModelFactory
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        LoadDealsUseCaseModule::class
    ]
)
class DealsListFragmentModule {

    @Provides
    @PerFragment
    @ForFragment
    internal fun provideContext(fragment: DealsListFragment): Context = fragment.requireContext()

    @Provides
    @PerFragment
    fun provideDealsListViewModel(
        fragment: DealsListFragment,
        factory: DealsListViewModelFactory
    ): DealsListViewModel = ViewModelProvider(fragment, factory).get(DealsListViewModel::class.java)

    @Provides
    @PerFragment
    fun provideFragmentManager(
        fragment: DealsListFragment
    ): FragmentManager = fragment.childFragmentManager

    @Provides
    @PerFragment
    internal fun provideLayoutManager(@ForFragment context: Context): RecyclerView.LayoutManager =
        LinearLayoutManager(context)

    @Provides
    @PerFragment
    internal fun provideDividerItemDecoration(@ForFragment context: Context) =
        DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

}