package com.fake.marketplace.presentation.screens.detailProduct.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fake.marketplace.databinding.ItemCharacteristicsBinding
import com.fake.marketplace.domain.entities.product.InfoEntity

class CharactAdapter: ListAdapter<InfoEntity, CharactViewHolder>(CharactDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacteristicsBinding.inflate(inflater, parent, false)
        return CharactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding){
            tvName.text = item.title
            tvValue.text = item.value
        }
    }
}