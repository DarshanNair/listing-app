package com.target.targetcasestudy.dealslist.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.target.targetcasestudy.core.imageloader.ImageLoader
import com.target.targetcasestudy.dealslist.model.DealItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.deal_list_item.*

class DealItemViewHolder(
    override val containerView: View,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(dealItem: DealItem) {
        product_title.text = dealItem.title
        product_price.text = dealItem.price
        aisle_info.text = dealItem.aisle
        imageLoader.draw(product_image, dealItem.url)
    }

}