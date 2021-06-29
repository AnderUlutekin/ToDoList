package com.example.todolist.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat

@Entity(tableName = "item_table")
@Parcelize
data class Item(
    val message: String,
    val completed: Boolean = false,
    val date: Long = 0,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable