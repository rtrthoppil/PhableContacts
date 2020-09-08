package com.rtr.phablecontacts.view.fragement

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rtr.phablecontacts.R
import com.rtr.phablecontacts.base.BaseFragment
import com.rtr.phablecontacts.databinding.FragmentDetailsBinding
import com.rtr.phablecontacts.db.Contacts
import com.rtr.phablecontacts.viewmodel.DetailsViewModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Fragment class for details screen
 */

const val KEY_ARGUMENT_DATA = "contact"

class DetailsFragment  : BaseFragment() {

    lateinit var viewModel: DetailsViewModel
    lateinit var binding: FragmentDetailsBinding

    companion object {
        fun newInstance(data : Contacts? = null): DetailsFragment {
            val fragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY_ARGUMENT_DATA, data)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        addObservers()
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
        addTextWatcherForViews()
        getArgumentData()
    }

    /**
     * Method to get argument data
     */
    private fun getArgumentData(){
        arguments?.let {
            viewModel.initializeData(it.get(KEY_ARGUMENT_DATA) as Contacts?)
        }
    }

    /**
     * Method to add observers
     */
    private fun addObservers(){
        viewModel.isActionCompleted.observe(this, Observer {
            if(it == true) {
                if(viewModel.isNewRecord.get())
                    Toast.makeText(context, getString(R.string.add_success),Toast.LENGTH_SHORT).show()
                else
                    Toast.makeText(context, getString(R.string.update_success),Toast.LENGTH_SHORT).show()
                viewModel.showProgressView(false)
                binding.editTextEmail.clearFocus()
                binding.editTextName.clearFocus()
                activity?.supportFragmentManager?.popBackStackImmediate()
            }
        })
    }

    /**
     * Method to add text watchers
     */
    private fun addTextWatcherForViews() {
        binding.editTextName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.errorTextName.set(null)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                /* Left blank intentionally */
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                /* Left blank intentionally */
            }
        })

        binding.editTextEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.errorTextEmail.set(null)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                /* Left blank intentionally */
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                /* Left blank intentionally */
            }
        })
    }
}