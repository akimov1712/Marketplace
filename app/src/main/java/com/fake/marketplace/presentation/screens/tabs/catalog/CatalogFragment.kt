package com.fake.marketplace.presentation.screens.tabs.catalog

import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.forEach
import com.denzcoskun.imageslider.models.SlideModel
import com.fake.marketplace.Const.imageWithIdMap
import com.fake.marketplace.R
import com.fake.marketplace.databinding.FragmentCatalogBinding
import com.fake.marketplace.presentation.base.BaseFragment
import com.google.android.material.chip.Chip


class CatalogFragment : BaseFragment<FragmentCatalogBinding>(FragmentCatalogBinding::inflate) {

    private fun openPopupMenuSortedList() {
        val popup = PopupMenu(requireContext(), binding.btnChoiceSorted)
        popup.menuInflater.inflate(R.menu.sorted_menu, popup.menu)
        popup.setForceShowIcon(true)
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_popular -> {
                    binding.tvSorted.setText(R.string.sorted_popular)
                    true
                }
                R.id.menu_price_ask -> {
                    binding.tvSorted.setText(R.string.sorted_price_asc)
                    true
                }
                R.id.menu_price_desc -> {
                    binding.tvSorted.setText(R.string.sorted_price_desc)
                    true
                }
                else -> { false }
            }
        }
        popup.show()
    }

    override fun setListenersInView() {
        super.setListenersInView()
        setListenersInChips()
        with(binding){
            btnChoiceSorted.setOnClickListener {
                openPopupMenuSortedList()
            }
        }
    }

    private fun setListenersInChips() {
        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            group.forEach {
                val chip = it as Chip
                chip.isCloseIconVisible = chip.isChecked
            }
            if (checkedIds.isEmpty()) {
                Toast.makeText(requireContext(), "Ничего не активно", Toast.LENGTH_SHORT).show()
            }
            when (group.checkedChipId) {
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