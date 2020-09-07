package com.rtr.phablecontacts.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Custom binding adapter
 */

@BindingAdapter("errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    if(errorMessage == null) view.error = null
    else view.error = errorMessage
}