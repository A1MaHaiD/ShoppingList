package com.handroid.shoppinglist.presentation.adapters

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.handroid.shoppinglist.R

@BindingAdapter("errorInputName")
fun bindErrorInputName(textInputLayout: TextInputLayout,isError:Boolean){
    val errorMessage = if (isError) {
        textInputLayout.context.getString(R.string.error_input_name)
    } else {
        null
    }
    textInputLayout.error = errorMessage
}

@BindingAdapter("errorInputCount")
fun bindErrorInputCount(textInputLayout: TextInputLayout,isError:Boolean){
    val errorMessage = if (isError) {
        textInputLayout.context.getString(R.string.error_input_count)
    } else {
        null
    }
    textInputLayout.error = errorMessage
}
