package com.fake.marketplace.presentation.screens.signIn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fake.marketplace.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val application: Application
): AndroidViewModel(application) {

    private val _state = MutableStateFlow<SignInState>(SignInState.Initial)
    val state get() = _state.asStateFlow()

    fun signIn(name: String, surname: String, numberPhone: String){
        val parseName = parseString(name)
        val parseSurname = parseString(surname)
        val parseNumberPhone = parseString(numberPhone)
        viewModelScope.launch {
            // вызов useCase для авторизации
            _state.value = SignInState.AuthorizationSuccessfull
        }
    }

    fun checkUpdateEnableButton(
        validName : Boolean,
        validSurname: Boolean,
        validNumberPhone: Boolean,
    ){
        if (validName && validSurname && validNumberPhone){
            _state.value = SignInState.StateEnabledButton(true)
        } else {
            _state.value = SignInState.StateEnabledButton(false)
        }
    }

    fun validName(text: String){
        val parseName = parseString(text)
        if (parseName.isBlank()){
            _state.value = SignInState.ValidInputName(
                false,
                application.getString(R.string.field_cannot_empty)
            )
            return
        }
        if (parseName.length <= 1){
            _state.value = SignInState.ValidInputName(
                false,
                application.getString(R.string.field_have_2_chars)
            )
            return
        }
        _state.value = SignInState.ValidInputName(
            true,
            ""
        )
    }

    fun validSurname(text: String){
        val parseSurname = parseString(text)
        if (parseSurname.isBlank()){
            _state.value = SignInState.ValidInputSurname(
                false,
                application.getString(R.string.field_cannot_empty)
            )
            return
        }
        if (parseSurname.length <= 1){
            _state.value = SignInState.ValidInputSurname(
                false,
                application.getString(R.string.field_have_2_chars)
            )
            return
        }
        _state.value = SignInState.ValidInputSurname(
            true,
            ""
        )
    }

    fun validNumberPhone(text: String){
        val parseNumberPhone = parseString(text)
        if (parseNumberPhone.length != 12){
            _state.value = SignInState.ValidInputNumberPhone(
                false,
                application.getString(R.string.check_right_input_number)
            )
            return
        }
        if (parseNumberPhone.isBlank()){
            _state.value = SignInState.ValidInputNumberPhone(
                false,
                application.getString(R.string.field_cannot_empty)
            )
            return
        }
        _state.value = SignInState.ValidInputNumberPhone(
            true,
            ""
        )
    }


    private fun parseString(inputName: String?): String {
        return inputName?.trim()?.filter { it != ' '} ?: ""
    }

}