package com.rtr.phablecontacts.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rtr.phablecontacts.R
import com.rtr.phablecontacts.databinding.ActivityBaseBinding

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Base activity class for common methods related to activities
 */
open class BaseActivity : AppCompatActivity() {

    private lateinit var activityBaseBinding : ActivityBaseBinding
    private lateinit var activityViewModel : BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
    }

    /**
     * Method to set content view for activity
     */
    fun setContentViewForActivity(layoutId : Int, viewModel: BaseViewModel, showHeader : Boolean = false) : ViewDataBinding {
        activityBaseBinding.viewModel = viewModel
        activityViewModel = viewModel
        return DataBindingUtil.inflate(layoutInflater, layoutId, activityBaseBinding.layoutBaseActivityContent, true)
    }

    /**
     * Method to attach first fragment to the activity without using an activity layout
     */
    fun attachFragmentToActivity(fragment : BaseFragment, tag : String, viewModel: BaseViewModel){
        activityBaseBinding.viewModel = viewModel
        supportFragmentManager.beginTransaction().add(activityBaseBinding.layoutBaseActivityContent.id, fragment, tag ).commit()
    }

    /**
     * Method to add remaining fragment to the activity without using an activity layout
     */
    fun addFragmentToActivity(fragment : BaseFragment, tag : String, viewModel: BaseViewModel){
        activityBaseBinding.viewModel = viewModel
        supportFragmentManager.beginTransaction().add(activityBaseBinding.layoutBaseActivityContent.id, fragment, tag ).addToBackStack(tag).commit()
    }

    /**
     * Method to replace remaining fragment to the activity without using an activity layout
     */
    fun replaceFragmentToActivity(fragment : BaseFragment, tag : String, viewModel: BaseViewModel){
        activityBaseBinding.viewModel = viewModel
        supportFragmentManager.beginTransaction().replace(activityBaseBinding.layoutBaseActivityContent.id, fragment, tag ).addToBackStack(tag).commit()
    }
}