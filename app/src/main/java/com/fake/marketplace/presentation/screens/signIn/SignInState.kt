package com.fake.marketplace.presentation.screens.signIn

sealed class SignInState {

    object Initial: SignInState()
    object ErrorSignIn: SignInState()
    object AuthorizationSuccessfull: SignInState()

    data class ValidInputName(val valid: Boolean, val errorText: String): SignInState()
    data class ValidInputSurname(val valid: Boolean, val errorText: String): SignInState()
    data class ValidInputNumberPhone(val valid: Boolean, val errorText: String): SignInState()

    data class StateEnabledButton(val enabled: Boolean): SignInState()

}