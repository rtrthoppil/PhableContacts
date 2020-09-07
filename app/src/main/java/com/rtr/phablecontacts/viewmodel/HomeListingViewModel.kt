package com.rtr.phablecontacts.viewmodel

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rtr.phablecontacts.base.BaseViewModel
import com.rtr.phablecontacts.db.AppDataBase
import com.rtr.phablecontacts.db.Contacts
import com.rtr.phablecontacts.repository.ContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * View model for home listing screen
 */
class HomeListingViewModel(var app: Application) : BaseViewModel(app) {

    private val contactsRepository: ContactsRepository
    var contactList: LiveData<List<Contacts>> = MutableLiveData()
    var isDataEmpty : ObservableBoolean = ObservableBoolean(true)

    init {
        val contactsDao = AppDataBase.getDbInstance(appContext, viewModelScope).contactsDao()
        contactsRepository = ContactsRepository(contactsDao)
        contactList = contactsRepository.contactList
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun addContact(contact: Contacts) = viewModelScope.launch(Dispatchers.IO) {
        contactsRepository.addContact(contact)
    }

    /**
     * Launching a new coroutine to delete the data in a non-blocking way
     */
    fun deleteContact(contact: Contacts) = viewModelScope.launch(Dispatchers.IO) {
        contactsRepository.deleteContact(contact)
    }

}

