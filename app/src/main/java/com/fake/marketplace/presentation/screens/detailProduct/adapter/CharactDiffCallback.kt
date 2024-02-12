package com.fake.marketplace.presentation.screens.detailProduct.adapter

import androidx.recyclerview.widget.DiffUtil
import com.fake.marketplace.domain.entities.product.InfoEntity

class CharactDiffCallback: DiffUtil.ItemCallback<InfoEntity>() {

    override fun areItemsTheSame(oldItem: InfoEntity, newItem: InfoEntity): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: InfoEntity, newItem: InfoEntity): Boolean {
        return oldItem == newItem
    }
}