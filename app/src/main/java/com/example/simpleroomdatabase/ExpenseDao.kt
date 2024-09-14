package com.example.simpleroomdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExpenseDao {

    @Query("select * from Expense")
    fun getAllExpense():List<Expance>

    @Insert
    fun adddata(expance: Expance)


    @Update
    fun  updatedata(expance: Expance)

    @Delete
    fun deletedata(expance: Expance)
}