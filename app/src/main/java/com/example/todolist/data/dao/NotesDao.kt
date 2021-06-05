package com.example.todolist.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolist.data.entity.Note

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewNote(note : Note)

    @Query("Select * from  Note")
    fun getAllNotes(): LiveData<List<Note>>

    @Delete
    suspend fun deleteNote(note : Note)

    @Query("Delete from Note")
    suspend fun deleteAllNote()

    @Update
    suspend fun updateNote(note : Note)
}