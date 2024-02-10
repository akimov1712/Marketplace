package com.fake.marketplace.presentation.screens.tabs.profile

import com.fake.marketplace.domain.entities.account.AccountEntity

sealed class ProfileState {

    data object Initial: ProfileState()
    data object LogOut: ProfileState()
    data object ErrorLogOut: ProfileState()
    data object ErrorAuthorization: ProfileState()
    data class Account(val account: AccountEntity): ProfileState()
    data class CountFavoriteProduct(val count: Int): ProfileState()


}