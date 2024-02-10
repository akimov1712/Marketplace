package com.fake.marketplace.presentation.screens.tabs.profile.favoriteProducts

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fake.marketplace.databinding.FragmentFavoriteBinding
import com.fake.marketplace.presentation.base.BaseFragment
import com.fake.marketplace.presentation.base.productAdapter.ProductAdapter
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

    private fun setProductAdapter(){
        adapter.setOnFavoriteClickListener = { id: String, isFavorite: Boolean ->
            viewModel.updateFavoriteState(id, isFavorite)
        }
        adapter.setOnItemClickListener = {

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