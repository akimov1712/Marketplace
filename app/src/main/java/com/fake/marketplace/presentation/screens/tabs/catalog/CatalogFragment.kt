package com.fake.marketplace.presentation.screens.tabs.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.forEach
import com.fake.marketplace.R
import com.fake.marketplace.databinding.FragmentCatalogBinding
import com.fake.marketplace.databinding.FragmentDiscountBinding
import com.fake.marketplace.presentation.base.BaseFragment
import com.google.android.material.chip.Chip


class CatalogFragment : BaseFragment<FragmentCatalogBinding>(FragmentCatalogBinding::inflate){

    override fun setViews() {
        super.setViews()

    }

    override fun setListenersInView() {
        super.setListenersInView()
        setListenersInChips()

    }

    private fun setListenersInChips(){
        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            group.forEach {
                val chip = it as Chip
                chip.isCloseIconVisible = chip.isChecked
            }
            if (checkedIds.isEmpty()){
                Toast.makeText(requireContext(), "Ничего не активно", Toast.LENGTH_SHORT).show()
            }
            when(group.checkedChipId){
                R.id.chip_show_all -> {
                    Toast.makeText(requireContext(), "Смотреть все", Toast.LENGTH_SHORT).show()
                }
                R.id.chip_face -> {
                    Toast.makeText(requireContext(), "Лицо", Toast.LENGTH_SHORT).show()
                }
                R.id.chip_body -> {
                    Toast.makeText(requireContext(), "Тело", Toast.LENGTH_SHORT).show()
                }
                R.id.chip_tan -> {
                    Toast.makeText(requireContext(), "Загар", Toast.LENGTH_SHORT).show()
                }
                R.id.chip_mask -> {
                    Toast.makeText(requireContext(), "Маска", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}