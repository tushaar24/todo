package com.example.todolist.fragment.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.entity.Note
import com.example.todolist.databinding.ToDoItemBinding
import com.example.todolist.fragment.addnote.AddNoteViewModel
import com.example.todolist.fragment.modifynote.ModifyNote
import java.lang.Exception

class ToDoListAdapter(val onClick : (note : Note) -> Unit) : RecyclerView.Adapter<ToDoListAdapter.ToDoListAdapterViewHolder>() {

  var notesList : MutableList<Note> = mutableListOf()

     class ToDoListAdapterViewHolder(private val binding : ToDoItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun bind(note : Note){
                binding.tvTitle.text = note.title
                binding.tvDescription.text = note.decripion
            }

        companion object{
            fun from(parent : ViewGroup): ToDoListAdapterViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ToDoItemBinding.inflate(layoutInflater, parent, false)
                return ToDoListAdapterViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListAdapterViewHolder {
        return ToDoListAdapterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ToDoListAdapterViewHolder, position: Int) {
        val currentNote = notesList[position]
        holder.bind(currentNote)
        holder.itemView.setOnClickListener {
            onClick(currentNote)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setData(noteList : List<Note>){
        notesList.clear()
        for(note in noteList){
            notesList.add(note)
        }
        notifyDataSetChanged()
    }
}