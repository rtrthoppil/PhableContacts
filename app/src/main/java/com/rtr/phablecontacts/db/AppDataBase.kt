package com.rtr.phablecontacts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * class for data base versioning
 */
const val DB_NAME = "contacts_db"

@Database(entities = [Contacts::class], version = 1)
abstract class AppDataBase : RoomDatabase(){

    abstract fun contactsDao() : ContactsDao

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDbInstance(context: Context,  scope: CoroutineScope) : AppDataBase{
            if(INSTANCE != null) return INSTANCE as AppDataBase
            synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext, AppDataBase::class.java,DB_NAME)
                    .addCallback(ContactsDatabaseCallback(scope))
                    .build()
                return INSTANCE as AppDataBase
            }
        }
    }

    private class ContactsDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.contactsDao())
                }
            }
        }

        suspend fun populateDatabase(contactsDao: ContactsDao) {

        }
    }
}

