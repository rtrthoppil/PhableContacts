package com.rtr.phablecontacts.view.fragement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.rtr.phablecontacts.R
import com.rtr.phablecontacts.base.BaseFragment
import com.rtr.phablecontacts.databinding.FragmentDetailsBinding
import com.rtr.phablecontacts.viewmodel.DetailsViewModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Fragment class for details screen
 */
class DetailsFragment  : BaseFragment() {

    lateinit var viewModel: DetailsViewModel
    lateinit var binding: FragmentDetailsBinding

    companion object {
        fun newInstance(): DetailsFragment {
            return DetailsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val parentView = super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        setContentViewForFragment(binding.root, viewModel)
        return parentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}