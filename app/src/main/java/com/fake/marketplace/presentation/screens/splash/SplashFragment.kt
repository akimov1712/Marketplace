package com.fake.marketplace.presentation.screens.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fake.marketplace.databinding.FragmentSplashBinding
import com.fake.marketplace.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel by viewModels<SplashViewModel>()

    override fun setViews() {
        super.setViews()
        setLoader()
    }

    private fun setLoader(){
        lifecycleScope.launch {
            delay(2000)
            viewModel.checkSignInUser()
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        lifecycleScope.launch {
            viewModel.state.collect{
                when(it){
                    is SplashState.SignInUser -> {
                        val direction = if (it.result){
                            SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                        } else {
                            SplashFragmentDirections.actionSplashFragmentToSignInFragment()
                        }
                        findNavController().navigate(direction)
                    } else -> {}
                }
            }
        }
    }
}