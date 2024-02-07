package com.fake.marketplace.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fake.marketplace.domain.useCases.account.CheckSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkSignInUseCase: CheckSignInUseCase
): ViewModel() {

    private val _state = MutableStateFlow<SplashState>(SplashState.Initial)
    val state = _state.asStateFlow()

    fun checkSignInUser() = viewModelScope.launch {
        val result = checkSignInUseCase()
        _state.value = SplashState.SignInUser(result)
    }
}