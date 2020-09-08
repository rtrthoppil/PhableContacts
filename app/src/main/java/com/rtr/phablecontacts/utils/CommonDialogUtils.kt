package com.rtr.phablecontacts.utils

import android.app.AlertDialog
import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.rtr.phablecontacts.R

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Common alert dialog utils class
 */
object CommonDialogUtils {

    /**
     * Method to show common alert dialog
     */
    fun showCommonAlertDialog(context: Context, message : String, positiveButton : String = context.getString(
        R.string.confirm),
                              title: String? = null, negativeButton: String?= null,
                              isCancelable : Boolean = false, dialogClickListener : DialogClickListener?= null) {
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
            .setCancelable(isCancelable)
            .setPositiveButton(positiveButton) { _, _ -> dialogClickListener?.onClickPositiveButton() }
        negativeButton?.let{ builder.setNegativeButton(it) { _, _ -> dialogClickListener?.onClickNegativeButton() } }
        title?.let { builder.setTitle(it) }
        builder.create().show()
    }
}