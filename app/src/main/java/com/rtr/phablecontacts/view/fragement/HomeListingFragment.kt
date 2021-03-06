package com.rtr.phablecontacts.view.fragement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rtr.phablecontacts.R
import com.rtr.phablecontacts.base.BaseFragment
import com.rtr.phablecontacts.databinding.FragmentHomeListingBinding
import com.rtr.phablecontacts.db.Contacts
import com.rtr.phablecontacts.utils.CommonDialogUtils
import com.rtr.phablecontacts.utils.DialogClickListener
import com.rtr.phablecontacts.utils.OnClickContactItem
import com.rtr.phablecontacts.utils.OnClickListenerForContacts
import com.rtr.phablecontacts.view.adapter.ContactsRecyclerViewAdapter
import com.rtr.phablecontacts.viewmodel.HomeListingViewModel

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Fragment class for home listing screen
 */
class HomeListingFragment : BaseFragment(), OnClickContactItem {

    lateinit var viewModel: HomeListingViewModel
    lateinit var binding: FragmentHomeListingBinding
    private lateinit var callbackOnClickListenerForContacts: OnClickListenerForContacts

    companion object {
        fun newInstance(): HomeListingFragment {
            return HomeListingFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeListingViewModel::class.java)
        addObservers()
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
        callbackOnClickListenerForContacts.onCLickFabButton()
    }

    /**
     * Method to attach listener for fab button
     */
    fun setOnClickFabButtonAction(callbackOnClickListenerForContacts: OnClickListenerForContacts) {
        this.callbackOnClickListenerForContacts = callbackOnClickListenerForContacts
    }

    override fun onClickContactItem(view: View, item : Contacts) {
        showConfirmMessage(item)
    }

    /**
     * Method to show the pop-up for delete and edit option
     */
    private fun showConfirmMessage(item : Contacts){
        context?.let { context ->
            CommonDialogUtils.showCommonAlertDialog(context, message = getString(R.string.are_you_sure),
                title = getString(R.string.confirm_pop_up), positiveButton = getString(R.string.remove),
                negativeButton = getString(R.string.update), dialogClickListener = object :
                    DialogClickListener {
                    override fun onClickPositiveButton() {
                        viewModel.showProgressView(true)
                        viewModel.deleteContact(item)
                        viewModel.showProgressView(false)
                    }
                    override fun onClickNegativeButton() {
                        callbackOnClickListenerForContacts.onClickUpdateItem(item)
                    }
                })
        }
    }

    /**
     * Method to add observers
     */
    private fun addObservers(){
        viewModel.contactList.observe(this, Observer {
            if(it.isNotEmpty()) updateAdapterForContactList(it as MutableList<Contacts>)
            else viewModel.isDataEmpty.set(true)
            viewModel.showProgressView(false)
        })
    }

    /**
     * Method to initialize the contact list recyclerview adapter
     */
    private fun initializeAdapterForContactList(contactList : MutableList<Contacts>){
        binding.recyclerViewContacts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = ContactsRecyclerViewAdapter(contactList, this@HomeListingFragment)
        }
    }

    /**
     * Method to update the contact list of recyclerview adapter
     */
    private fun updateAdapterForContactList(contactList : MutableList<Contacts>?){
        if(contactList != null && contactList?.size > 0){
            viewModel.isDataEmpty.set(false)
            val adapter = binding.recyclerViewContacts.adapter
            if(adapter != null && adapter is ContactsRecyclerViewAdapter) adapter.updateContactList(contactList)
            else initializeAdapterForContactList(contactList)
        }else viewModel.isDataEmpty.set(true)
    }
}