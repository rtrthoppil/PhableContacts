package com.rtr.phablecontacts.view

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.rtr.phablecontacts.R
import com.rtr.phablecontacts.base.BaseActivity
import com.rtr.phablecontacts.databinding.ActivityHomeBinding
import com.rtr.phablecontacts.utils.OnClickFabButton
import com.rtr.phablecontacts.viewmodel.HomeViewModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Activity class for home screen
 */
class HomeActivity : BaseActivity(), OnClickFabButton{

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = setContentViewForActivity(R.layout.activity_home, viewModel) as ActivityHomeBinding
        binding.viewModel = viewModel
        launchHomeListing()
    }

    /**
     * Method to load home listing screen
     */
    private fun launchHomeListing(){
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        supportFragmentManager.beginTransaction().replace(binding.layoutHomeContent.id,
            HomeListingFragment.newInstance(), HomeListingFragment::class.java.toString() ).commit()
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is HomeListingFragment) {
            fragment.setOnClickFabButtonAction(this)
        }
    }

    override fun onCLickFabButton() {
        Toast.makeText(this,"Clicked", Toast.LENGTH_SHORT).show()
    }
}