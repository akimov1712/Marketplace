package com.fake.marketplace.presentation.screens.signIn

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class FormatPhoneTextWatcher(
    private val editText: EditText,
    val onTextChangedListener: (String) -> Unit
) : TextWatcher {

    private var isFormatting: Boolean = false
    private val phoneFormat = "+X XXX XXX XX XX"


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
        onTextChangedListener(s.toString())
        if (!isFormatting) {
            isFormatting = true
            formatPhoneNumber(s)
            isFormatting = false
        }
    }

    private fun formatPhoneNumber(s: Editable?) {
        if (s.isNullOrEmpty()) return

        val digits = s.toString().replace("\\D".toRegex(), "")
        var formatted = ""
        var index = 0

        for (i in 0 until phoneFormat.length) {
            if (index >= digits.length) break

            val placeholder = phoneFormat[i]
            if (placeholder == 'X') {
                formatted += digits[index]
                index++
            } else {
                formatted += placeholder
            }
        }

        editText.removeTextChangedListener(this)
        editText.setText(formatted)
        editText.setSelection(formatted.length)
        editText.addTextChangedListener(this)
    }
}