package com.fake.marketplace.presentation.screens.tabs.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fake.marketplace.data.DatabaseEmptyException
import com.fake.marketplace.domain.useCases.account.CheckSignInUseCase
import com.fake.marketplace.domain.useCases.account.GetAccountUseCase
import com.fake.marketplace.domain.useCases.account.LogOutUseCase
import com.fake.marketplace.domain.useCases.product.GetCountFavoriteProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAccountUseCase: GetAccountUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val checkSignInUseCase: CheckSignInUseCase,
    private val getCountFavoriteProductUseCase: GetCountFavoriteProductUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ProfileState>(ProfileState.Initial)
    val state = _state.asStateFlow()

    fun getAccount() = viewModelScope.launch {
        try {
            getAccountUseCase().collect{
                _state.value = ProfileState.Account(it)
            }
        } catch (e: DatabaseEmptyException){
            _state.value = ProfileState.ErrorAuthorization
        }
    }

    fun getCountFavoriteProduct() = viewModelScope.launch {
        val count = getCountFavoriteProductUseCase()
        if (count != 0){
            _state.value = ProfileState.CountFavoriteProduct(count)
        }
    }

    fun logOut() = viewModelScope.launch {
        logOutUseCase()
        val isSignIn = checkSignInUseCase()
        if (!isSignIn) {
            _state.value = ProfileState.LogOut
        } else {
            _state.value = ProfileState.ErrorLogOut
        }
    }

    init {
        getCountFavoriteProduct()
        getAccount()
    }

}