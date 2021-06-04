package com.example.todolist.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class Note(
    var title : String,
    var decripion : String,
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
)