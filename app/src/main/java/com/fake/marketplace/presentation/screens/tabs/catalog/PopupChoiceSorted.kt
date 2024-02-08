package com.fake.marketplace.presentation.screens.tabs.catalog

import android.content.Context
import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContentProviderCompat.requireContext
import com.fake.marketplace.R

object PopupChoiceSorted {

    private fun setOpenMenuInEmployees(context: Context, anchorView: View) {
        val popup = PopupMenu(context, anchorView)
        popup.menuInflater.inflate(R.menu.sorted_menu, popup.menu)
        popup.setForceShowIcon(true)
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_popular -> {
                    true
                }
                R.id.menu_price_ask -> {
                    true
                }
                R.id.menu_price_desc -> {
                    true
                }
                else -> { false }
            }
        }
        popup.show()
    }

}