package com.example.todolist.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Note")
@Parcelize
data class Note(
    var title : String,
    var decripion : String,
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
) : Parcelable