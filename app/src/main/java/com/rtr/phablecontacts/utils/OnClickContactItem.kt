package com.rtr.phablecontacts.utils

import android.view.View
import com.rtr.phablecontacts.model.ContactItem

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Interface for contact item click listener
 */
interface OnClickContactItem {

    fun onClickContactItem(view : View, item : ContactItem)

}