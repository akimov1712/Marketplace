package com.fake.marketplace.presentation.screens.tabs.catalog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.forEach
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fake.marketplace.Const
import com.fake.marketplace.Const.BODY_TAG
import com.fake.marketplace.Const.FACE_TAG
import com.fake.marketplace.Const.MASK_TAG
import com.fake.marketplace.Const.SHOW_ALL_TAG
import com.fake.marketplace.Const.SUNTAN_TAG
import com.fake.marketplace.R
import com.fake.marketplace.databinding.FragmentCatalogBinding
import com.fake.marketplace.domain.entities.SortedTypeEnum
import com.fake.marketplace.presentation.base.BaseFragment
import com.fake.marketplace.presentation.screens.tabs.catalog.adapter.ProductAdapter
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatalogFragment : BaseFragment<FragmentCatalogBinding>(FragmentCatalogBinding::inflate) {

    private val viewModel by viewModels<CatalogViewModel>()
    private val adapter by lazy { ProductAdapter() }

    private var sortedType = SortedTypeEnum.POPULARITY_SORTED_TYPE
    private var choiceTag = SHOW_ALL_TAG

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProductList(tag = choiceTag, sortType = sortedType)
    }

    override fun observeViewModel() {
        super.observeViewModel()
        with(binding){
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED){
                    viewModel.state.collect{
                        when(it){
                            is CatalogState.ProductList -> {
                                adapter.submitList(it.list)
                                rvProducts.visibility = View.VISIBLE
                                loader.visibility = View.GONE
                            }
                            is CatalogState.Loading -> {
                                rvProducts.visibility = View.GONE
                                loader.visibility = View.VISIBLE
                            }
                            is CatalogState.ErrorLoadingData -> {
                                rvProducts.visibility = View.GONE
                                loader.visibility = View.GONE
                                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }

    }

    override fun setRecyclerViews() {
        super.setRecyclerViews()
        binding.rvProducts.adapter = adapter
    }

    override fun setListenersInView() {
        super.setListenersInView()
        setListenersInChips()
        with(binding) {
            btnChoiceSorted.setOnClickListener {
                openPopupMenuSortedList()
            }
        }
    }

    private fun openPopupMenuSortedList() {
        val popup = PopupMenu(requireContext(), binding.btnChoiceSorted)
        popup.menuInflater.inflate(R.menu.sorted_menu, popup.menu)
        popup.setForceShowIcon(true)
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_popular -> {
                    binding.tvSorted.setText(R.string.sorted_popular)
                    sortedType = SortedTypeEnum.POPULARITY_SORTED_TYPE
                    viewModel.sortedProductList(choiceTag, sortedType)
                    true
                }

                R.id.menu_price_ask -> {
                    binding.tvSorted.setText(R.string.sorted_price_asc)
                    sortedType = SortedTypeEnum.PRICE_ASK_SORTED_TYPE
                    viewModel.sortedProductList(choiceTag, sortedType)
                    true
                }

                R.id.menu_price_desc -> {
                    binding.tvSorted.setText(R.string.sorted_price_desc)
                    sortedType = SortedTypeEnum.PRICE_DESK_SORTED_TYPE
                    viewModel.sortedProductList(choiceTag, sortedType)
                    true
                }
                else -> {
                    false
                }
            }
        }
        popup.show()
    }

    private fun setListenersInChips() {
        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            group.forEach {
                val chip = it as Chip
                chip.isCloseIconVisible = chip.isChecked
            }
            if (checkedIds.isEmpty()) {
                choiceTag = SHOW_ALL_TAG
                viewModel.sortedProductList(choiceTag, sortedType)
            }
            when (group.checkedChipId) {
                R.id.chip_show_all -> {
                    choiceTag = SHOW_ALL_TAG
                    viewModel.sortedProductList(choiceTag, sortedType)
                }
                R.id.chip_face -> {
                    choiceTag = FACE_TAG
                    viewModel.sortedProductList(choiceTag, sortedType)
                }
                R.id.chip_body -> {
                    choiceTag = BODY_TAG
                    viewModel.sortedProductList(choiceTag, sortedType)
                }
                R.id.chip_tan -> {
                    choiceTag = SUNTAN_TAG
                    viewModel.sortedProductList(choiceTag, sortedType)
                }
                R.id.chip_mask -> {
                    choiceTag = MASK_TAG
                    viewModel.sortedProductList(choiceTag, sortedType)
                }
            }
        }
    }
}