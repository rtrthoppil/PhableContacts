package com.rtr.phablecontacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rtr.phablecontacts.R
import com.rtr.phablecontacts.base.BaseFragment
import com.rtr.phablecontacts.databinding.FragmentHomeListingBinding
import com.rtr.phablecontacts.utils.OnClickFabButton
import com.rtr.phablecontacts.viewmodel.HomeListingViewModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Fragment class for home listing screen
 */
class HomeListingFragment : BaseFragment() {

    lateinit var viewModel: HomeListingViewModel
    lateinit var binding: FragmentHomeListingBinding
    private lateinit var callbackOnClickFabButton: OnClickFabButton

    companion object {
        fun newInstance(): HomeListingFragment {
            return HomeListingFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeListingViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parentView = super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_listing, container, false)
        setContentViewForFragment(binding.root, viewModel)
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        addListeners()
    }

    /**
     * Method to add listeners for view
     */
    private fun addListeners(){
        binding.fabAddContact.setOnClickListener(fabClickListener)
    }

    /**
     * Click listener for fab button
     */
    private val fabClickListener = View.OnClickListener {
        callbackOnClickFabButton.onCLickFabButton()
    }

    /**
     * Method to attach listener for fab button
     */
    fun setOnClickFabButtonAction(callbackOnClickFabButton: OnClickFabButton) {
        this.callbackOnClickFabButton = callbackOnClickFabButton
    }

}