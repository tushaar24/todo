package com.example.todolist.fragment.addnote

import android.app.Application
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.database.Database
import com.example.todolist.data.entity.Note
import com.example.todolist.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch

class AddNoteViewModel(application: Application) : AndroidViewModel(application) {
    private val todoDao = Database.getDatabase(application).getDao()
    private val repository = Repository(todoDao)

    fun addNewNote(note : Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.addNewNote(note)
    }
    val noteList : LiveData<List<Note>> = repository.getAllNotes()

    fun deleteNote(note : Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }
}