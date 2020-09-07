package com.rtr.phablecontacts.viewmodel

import android.app.Application
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.rtr.phablecontacts.base.BaseViewModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * View model for details screen
 */
class DetailsViewModel(var app: Application) : BaseViewModel(app) {

    var name: ObservableField<String> = ObservableField()
    var email: ObservableField<String> = ObservableField()
    var isNewRecord: ObservableBoolean = ObservableBoolean(true)

    fun onCLickButtonSubmit(view: View) {

    }
}