package com.example.todolist.repository

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.Database
import com.example.todolist.data.dao.NotesDao
import com.example.todolist.data.entity.Note

class Repository(private val toDodao : NotesDao) {
    suspend fun addNewNote(note : Note){
        toDodao.addNewNote(note)
    }
    fun getAllNotes() : LiveData<List<Note>>{
       return  toDodao.getAllNotes()
    }

    suspend fun deleteNote(note : Note){
        toDodao.deleteNote(note)
    }

    suspend fun deleteAllNote(){
        toDodao.deleteAllNote()
    }

    suspend fun updateNote(note : Note) {
        toDodao.updateNote(note)
    }

    fun searchNote(query : String) : LiveData<List<Note>> = toDodao.searchDatabase(query)
}