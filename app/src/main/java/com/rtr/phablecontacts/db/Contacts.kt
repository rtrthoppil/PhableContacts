package com.rtr.phablecontacts.db

import android.view.View
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.rtr.phablecontacts.utils.OnClickContactItem

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Data entity class for table contacts
 */
@Entity
data class Contacts(
    @PrimaryKey(autoGenerate = true) val contactId: Int = 0,
    @ColumnInfo(name = "contactName") val contactName: String?,
    @ColumnInfo(name = "contactEmail") val contactEmail: String?){

    @Ignore
    var listener : OnClickContactItem? = null

    @Ignore
    fun onClickContactItem(view: View) {
        listener?.onClickContactItem(view, this)
    }
}