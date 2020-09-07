package com.rtr.phablecontacts.repository

import androidx.lifecycle.LiveData
import com.rtr.phablecontacts.db.Contacts
import com.rtr.phablecontacts.db.ContactsDao

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Repository class for contacts from db
 */
class ContactsRepository(private val contactsDao : ContactsDao) {

    val contactList : LiveData<List<Contacts>> = contactsDao.getAllContacts()

    suspend fun addContact(contact: Contacts) {
        contactsDao.addContacts(contact)
    }

    suspend fun deleteContact(contact: Contacts) {
        contactsDao.deleteContact(contact)
    }

    suspend fun deleteContact(contactId: Int) {
        contactsDao.deleteContact(contactId)
    }
}