package com.rtr.phablecontacts.db

import android.os.Parcelable
import android.view.View
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.rtr.phablecontacts.utils.OnClickContactItem
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Data entity class for table contacts
 */
@Parcelize
@Entity
data class Contacts(
    @PrimaryKey(autoGenerate = true) val contactId: Int = 0,
    @ColumnInfo(name = "contactName") var contactName: String?,
    @ColumnInfo(name = "contactEmail") var contactEmail: String?) : Parcelable{

    @IgnoredOnParcel
    @Ignore
    var listener : OnClickContactItem? = null

    @Ignore
    fun onClickContactItem(view: View) {
        listener?.onClickContactItem(view, this)
    }
}