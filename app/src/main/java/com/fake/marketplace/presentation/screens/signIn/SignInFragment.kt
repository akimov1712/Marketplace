package com.fake.marketplace.presentation.screens.signIn

import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fake.marketplace.R
import com.fake.marketplace.databinding.FragmentSignInBinding
import com.fake.marketplace.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val viewModel by viewModels<SignInViewModel>()

    private var validName = false
    private var validSurname = false
    private var validNumberPhone = false


    override fun observeViewModel() {
        super.observeViewModel()
        with(binding){
            lifecycleScope.launch {
                viewModel.state.collect{
                    when(it){
                        is SignInState.ValidInputName -> {
                            if (it.valid){
                                validName = true
                                tilName.isErrorEnabled = false
                            } else {
                                validName = false
                                tilName.error = it.errorText
                            }
                            checkButtonEnabled()
                        }
                        is SignInState.ValidInputSurname -> {
                            if (it.valid){
                                validSurname = true
                                tilSurname.isErrorEnabled = false
                            } else {
                                validSurname = false
                                tilSurname.error = it.errorText
                            }
                            checkButtonEnabled()
                        }
                        is SignInState.ValidInputNumberPhone -> {
                            if (it.valid){
                                validNumberPhone = true
                                tilNumberPhone.isErrorEnabled = false
                            } else {
                                validNumberPhone = false
                                tilNumberPhone.error = it.errorText
                            }
                            checkButtonEnabled()
                        }
                        is SignInState.StateEnabledButton -> {
                            btnSignIn.isEnabled = it.enabled
                        }
                        is SignInState.ErrorSignIn -> {
                            Toast.makeText(requireContext(),
                                getString(R.string.error_signIn), Toast.LENGTH_SHORT).show()
                        }
                        is SignInState.AuthorizationSuccessfull -> {
                            findNavController().navigate(
                                SignInFragmentDirections.actionSignInFragmentToHomeFragment()
                            )
                        }
                        else -> {}
                    }
                }
            }
        }
    }

    private fun checkButtonEnabled(){
        viewModel.checkUpdateEnableButton(
            validName,
            validSurname,
            validNumberPhone
        )
    }

    private fun setSettingInputName(){
        with(binding){
            etName.addTextChangedListener {
                viewModel.validName(it.toString())
            }
        }
    }

    private fun setSettingInputSurname(){
        with(binding){
            etSurname.addTextChangedListener {
                viewModel.validSurname(it.toString())
            }
        }
    }

    private fun setSettingInputNumberPhone(){
        with(binding){
            val phoneNumberFormattingTextWatcher = FormatPhoneTextWatcher(etNumberPhone){
                viewModel.validNumberPhone(it)
            }
            etNumberPhone.addTextChangedListener(phoneNumberFormattingTextWatcher)
            etNumberPhone.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (etNumberPhone.text?.isBlank() == true && hasFocus){
                    etNumberPhone.setText("+7")
                }
            }
        }

    }

    override fun setListenersInView() {
        super.setListenersInView()
        setSettingInputName()
        setSettingInputSurname()
        setSettingInputNumberPhone()
        with(binding){
            btnSignIn.setOnClickListener {
                val name = etName.text.toString()
                val surname = etSurname.text.toString()
                val numberPhone = etNumberPhone.text.toString()
                viewModel.signIn(
                    name,
                    surname,
                    numberPhone
                )
            }
        }
    }
}