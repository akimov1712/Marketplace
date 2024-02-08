package com.fake.marketplace.presentation.screens.tabs.catalog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.denzcoskun.imageslider.models.SlideModel
import com.fake.marketplace.Const.imageWithIdMap
import com.fake.marketplace.databinding.ItemProductBinding
import com.fake.marketplace.domain.entities.product.ProductEntity

class ProductAdapter: ListAdapter<ProductEntity, ProductViewHolder>(ProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding){
            val currency = item.price.unit
            val listImage = imageWithIdMap[item.id]?.map { SlideModel(it) } ?: emptyList()
            imageSlider.setImageList(listImage)
            tvOldPrice.text = "${item.price.price} $currency"
            tvPrice.text = "${item.price.priceWithDiscount} $currency"
            tvDiscount.text = "-${item.price.discount} %"
            tvTitle.text = "${item.title}"
            tvSubtitle.text = "${item.subTitle}"
            tvFeedbackRating.text = item.feedback.rating.toString()
            tvFeedbackCount.text = "(${item.feedback.count})"
        }
    }
}