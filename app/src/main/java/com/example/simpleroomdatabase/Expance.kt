package com.example.simpleroomdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Expense")
data class Expance(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "amount")
    var amount:String,
)
