package com.rtr.phablecontacts.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import com.rtr.phablecontacts.base.BaseViewModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * View model for home listing screen
 */
class HomeListingViewModel(var app: Application) : BaseViewModel(app) {

    var isDataEmpty : ObservableBoolean = ObservableBoolean(true)

}