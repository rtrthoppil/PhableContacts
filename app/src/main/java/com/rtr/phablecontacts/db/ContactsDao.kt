package com.rtr.phablecontacts.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Interface for contacts table related manipulations
 */
@Dao
interface ContactsDao {

    @Insert
    fun addContacts(contact: Contacts)

    @Delete
    fun deleteContact(contact: Contacts)

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contacts>>

    @Query("SELECT * FROM contacts WHERE contactId is :contactId")
    fun getContactById(contactId: Int): Contacts?

    @Query("DELETE FROM contacts WHERE contactId is :contactId")
    fun deleteContact(contactId: Int)
}