package com.fake.marketplace.presentation.screens.tabs.profile.favoriteProducts

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fake.marketplace.databinding.FragmentFavoriteBinding
import com.fake.marketplace.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    private val viewModel by viewModels<FavoriteViewModel>()

    override fun observeViewModel() {
        super.observeViewModel()
        with(binding) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.state.collect {
                        when (it) {
                            is FavoriteState.ProductList -> {

                            }

                            is FavoriteState.ErrorProductListEmpty -> {
                                Toast.makeText(requireContext(), "Список пуст", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            is FavoriteState.Loading -> {

                            }
                        }
                    }
                }
            }
        }
    }
}