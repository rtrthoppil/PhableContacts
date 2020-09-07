package com.rtr.phablecontacts.utils

import com.rtr.phablecontacts.db.Contacts

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Interface for fab button click action
 */
interface OnClickListenerForContacts {

    fun onCLickFabButton()

    fun onClickUpdateItem(item : Contacts)
}