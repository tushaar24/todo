package com.example.todolist.fragment.todolist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.entity.Note
import com.example.todolist.databinding.ToDoItemBinding

class ToDoListAdapter : RecyclerView.Adapter<ToDoListAdapter.ToDoListAdapterViewHolder>() {

    private var notesList : MutableList<Note> = mutableListOf()

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
    }

    override fun getItemCount(): Int {
        return notesList.size
    }
}