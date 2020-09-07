package com.rtr.phablecontacts.model

import android.os.Parcelable
import android.view.View
import androidx.databinding.ObservableField
import com.rtr.phablecontacts.utils.OnClickContactItem
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Data class for contact item
 */
@Parcelize
data class ContactItem(
    var contactName: ObservableField<String> = ObservableField(),
    var contactEmail: ObservableField<String> = ObservableField(),
    var contactId: String
) : Parcelable {

    @IgnoredOnParcel
    var listener : OnClickContactItem? = null

    fun onClickContactItem(view: View) {
        listener?.onClickContactItem(view, this)
    }
}