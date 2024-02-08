package com.fake.marketplace.presentation.screens.tabs

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.fake.marketplace.R
import com.fake.marketplace.databinding.FragmentTabsBinding
import com.fake.marketplace.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TabsFragment : BaseFragment<FragmentTabsBinding>(FragmentTabsBinding::inflate) {

    override fun setViews() {
        super.setViews()
        setBottomBar()
    }

    private fun setBottomBar(){
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.tabs_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomMenuTabs.setupWithNavController(navController)
    }

}