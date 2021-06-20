package com.target.targetcasestudy.dealslist.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.R
import com.target.targetcasestudy.core.imageloader.ImageLoader
import com.target.targetcasestudy.dealslist.model.DealItem
import javax.inject.Inject

class DealItemAdapter @Inject constructor(
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var clickCallback: (dealItem: DealItem) -> Unit
    private var dealItems = emptyList<DealItem>()

    fun setDealItems(dealItems: List<DealItem>) {
        this.dealItems = dealItems
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: (dealItem: DealItem) -> Unit) {
        clickCallback = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return DealItemViewHolder(view, imageLoader).apply {
            itemView.setOnClickListener {
                dealItems.getOrNull(adapterPosition)?.let { clickCallback(it) }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dealsListViewHolder = holder as? DealItemViewHolder
        dealsListViewHolder?.bind(dealItems[position])
    }

    override fun getItemCount() = dealItems.size

    override fun getItemViewType(position: Int) = R.layout.deal_list_item

}