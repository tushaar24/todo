package com.example.todolist.data.database

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.data.dao.NotesDao
import com.example.todolist.data.entity.Note

@androidx.room.Database(
    entities = [Note::class],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract fun getDao() : NotesDao

    companion object {
        @Volatile
        private var INSTANCE: Database? = null

        fun getDatabase(context: Context): Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}