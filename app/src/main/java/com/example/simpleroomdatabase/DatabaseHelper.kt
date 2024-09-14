package com.example.simpleroomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Expance::class], version = 1)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun userDao(): ExpenseDao

    companion object {
        private var dbInstance: DatabaseHelper? = null

        fun getDatabase(context: Context): DatabaseHelper {
            if (dbInstance == null) {
                dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHelper::class.java,
                    "users"
                ).allowMainThreadQueries().build()
            }
            return dbInstance!!
        }
    }
}