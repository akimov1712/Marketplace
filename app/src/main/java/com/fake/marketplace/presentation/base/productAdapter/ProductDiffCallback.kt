package com.fake.marketplace.presentation.base.productAdapter

import androidx.recyclerview.widget.DiffUtil
import com.fake.marketplace.domain.entities.product.ProductEntity

class ProductDiffCallback: DiffUtil.ItemCallback<ProductEntity>() {

    override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
        return oldItem == newItem
    }
}