package com.android.myjetpackcomponent.livedata.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Words")
data class Word (@PrimaryKey @ColumnInfo(name = "word") val word : String)