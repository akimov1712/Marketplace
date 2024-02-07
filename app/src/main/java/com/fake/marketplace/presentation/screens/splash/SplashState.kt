package com.fake.marketplace.presentation.screens.splash

sealed class SplashState {

    object Initial: SplashState()
    data class SignInUser(val result: Boolean): SplashState()

}