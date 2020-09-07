package com.rtr.phablecontacts.viewmodel

import android.app.Application
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rtr.phablecontacts.R
import com.rtr.phablecontacts.base.BaseViewModel
import com.rtr.phablecontacts.db.AppDataBase
import com.rtr.phablecontacts.db.Contacts
import com.rtr.phablecontacts.repository.ContactsRepository
import com.rtr.phablecontacts.utils.ValidationUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
    var isActionCompleted: MutableLiveData<Boolean> = MutableLiveData()
    var contactData : Contacts? = null
    var errorTextName : ObservableField<String> = ObservableField()
    var errorTextEmail : ObservableField<String> = ObservableField()

    private val contactsRepository: ContactsRepository

    init {
        val contactsDao = AppDataBase.getDbInstance(appContext, viewModelScope).contactsDao()
        contactsRepository = ContactsRepository(contactsDao)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    private fun addContact() = viewModelScope.launch(Dispatchers.IO) {
        contactsRepository.addContact(Contacts(contactName = name.get() ?: "", contactEmail = email.get() ?: ""))
        isActionCompleted.postValue(true)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    private fun updateContact() = viewModelScope.launch(Dispatchers.IO) {
        contactData?.contactName = name.get() ?: ""
        contactData?.contactEmail = email.get() ?: ""
        contactData?.let { contactsRepository.updateContact(it) }
        isActionCompleted.postValue(true)
    }

    /**
     * Method to validate the fields
     */
    private fun validateValues() : Boolean{
        var isValidData = true
        if(name.get().isNullOrEmpty()) {
            errorTextName.set(appContext.getString(R.string.error_name))
            isValidData = false
        }
        if(email.get().isNullOrEmpty() || !ValidationUtils.isValidEmail(email.get() ?: "")){
            errorTextEmail.set(appContext.getString(R.string.error_email))
            isValidData = false
        }
        return isValidData
    }

    /**
     * Method to set initial data
     */
    fun initializeData(data : Contacts?){
        contactData = data
        if(contactData != null) {
            name.set(contactData?.contactName ?: "")
            email.set(contactData?.contactEmail ?: "")
            isNewRecord.set(false)
        }
        else {
            name.set("")
            email.set("")
            isNewRecord.set(true)
        }
    }

    fun onCLickButtonSubmit(view: View) {
        if(validateValues()){
            showProgressView(true)
            if(isNewRecord.get())
                addContact()
            else
                updateContact()
        }
    }
}