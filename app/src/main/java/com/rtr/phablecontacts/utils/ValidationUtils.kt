package com.rtr.phablecontacts.utils

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Object class for validation checking
 */
object ValidationUtils {
    
    /**
     * Method to check email validity
     */
    fun isValidEmail(email : String?) : Boolean{
        email?.let{
            return android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
        }
        return false
    }

    /**
     * Method to check mobile validity
     */
    fun isValidMobile(mobile : String?) : Boolean{
        mobile?.let{
            return android.util.Patterns.PHONE.matcher(it).matches()
        }
        return false
    }

}