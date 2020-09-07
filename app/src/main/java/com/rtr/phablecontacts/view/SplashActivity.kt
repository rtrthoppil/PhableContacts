package com.rtr.phablecontacts.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rtr.phablecontacts.R
import com.rtr.phablecontacts.base.BaseActivity
import com.rtr.phablecontacts.databinding.ActivitySplashBinding
import com.rtr.phablecontacts.viewmodel.SplashViewModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Activity class for Splash screen
 */
class SplashActivity : BaseActivity(){

    private lateinit var viewModel: SplashViewModel
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding = setContentViewForActivity(R.layout.activity_splash, viewModel) as ActivitySplashBinding
        binding.viewModel = viewModel
    }
}