package com.fake.marketplace.presentation.screens.tabs.profile

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fake.marketplace.databinding.FragmentProfileBinding
import com.fake.marketplace.presentation.base.BaseFragment
import com.fake.marketplace.presentation.screens.tabs.TabsFragmentDirections
import com.fake.marketplace.utils.findTopNavController
import com.fake.marketplace.utils.formatPhoneNumber
import com.fake.marketplace.utils.productCountString
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel by viewModels<ProfileViewModel>()

    override fun setListenersInView() {
        super.setListenersInView()
        with(binding) {
            btnExit.setOnClickListener {
                viewModel.logOut()
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        with(binding) {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.state.collect {
                        when (it) {
                            is ProfileState.Account -> {
                                val nameSurname = "${it.account.name} ${it.account.surname}"
                                tvNameSurname.text = nameSurname
                                tvNumberPhone.text = it.account.numberPhone.formatPhoneNumber()
                            }

                            is ProfileState.CountFavoriteProduct -> {
                                tvCountFavorite.text = it.count.productCountString()
                            }

                            is ProfileState.LogOut -> {
                                findTopNavController().navigate(TabsFragmentDirections.actionTabsFragmentToSignInFragment())
                            }
                            is ProfileState.ErrorAuthorization -> {
                                Toast.makeText(requireContext(), "Вы не авторизованы", Toast.LENGTH_SHORT).show()
                                findTopNavController().navigate(TabsFragmentDirections.actionTabsFragmentToSignInFragment())
                            }
                            is ProfileState.ErrorLogOut -> {
                                Toast.makeText(
                                    requireContext(),
                                    "Произошла ошибка",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            is ProfileState.Initial -> {}
                        }
                    }
                }
            }
        }

    }
}