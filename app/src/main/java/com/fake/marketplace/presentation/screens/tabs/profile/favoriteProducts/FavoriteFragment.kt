package com.fake.marketplace.presentation.screens.tabs.profile.favoriteProducts

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.fake.marketplace.databinding.FragmentFavoriteBinding
import com.fake.marketplace.presentation.base.BaseFragment
import com.fake.marketplace.presentation.base.productAdapter.ProductAdapter
import com.fake.marketplace.presentation.screens.tabs.TabsFragmentDirections
import com.fake.marketplace.utils.findTopNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    private val viewModel by viewModels<FavoriteViewModel>()

    private val adapter by lazy { ProductAdapter() }

    override fun setAdapters() {
        super.setAdapters()
        setProductAdapter()
    }

    override fun setListenersInView() {
        super.setListenersInView()
        with(binding){
            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setProductAdapter(){
        adapter.setOnFavoriteClickListener = { id: String, isFavorite: Boolean ->
            viewModel.updateFavoriteState(id, isFavorite)
        }
        adapter.setOnItemClickListener = {
            findTopNavController().navigate(TabsFragmentDirections.actionTabsFragmentToDetailProductFragment(it))
        }
        binding.rvProducts.adapter = adapter
    }

    override fun observeViewModel() {
        super.observeViewModel()
        with(binding) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.state.collect {
                        when (it) {
                            is FavoriteState.ProductList -> {
                                loader.visibility = View.GONE
                                rvProducts.visibility = View.VISIBLE
                                adapter.submitList(it.list)
                            }

                            is FavoriteState.ErrorProductListEmpty -> {
                                loader.visibility = View.GONE
                                rvProducts.visibility = View.GONE
                                Toast.makeText(requireContext(), "Список пуст", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            is FavoriteState.Loading -> {
                                loader.visibility = View.VISIBLE
                                rvProducts.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }
    }
}