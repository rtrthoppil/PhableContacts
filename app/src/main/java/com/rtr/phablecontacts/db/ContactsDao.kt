package com.rtr.phablecontacts.db

import androidx.lifecycle.LiveData
import androidx.room.*

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

    @Update
    fun updateContact(contact: Contacts)

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): LiveData<List<Contacts>>

    @Query("SELECT * FROM contacts WHERE contactId is :contactId")
    fun getContactById(contactId: Int): Contacts?

    @Query("DELETE FROM contacts WHERE contactId is :contactId")
    fun deleteContact(contactId: Int)

}