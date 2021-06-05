package com.example.todolist.fragment.addnote

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.data.entity.Note
import com.example.todolist.databinding.FragmentAddNoteBinding

class AddNote : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val viewModel by lazy { AddNoteViewModel(requireActivity().application) }
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_note, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

         when(item.itemId){
            R.id.add_note -> {
                val note = Note(binding.etTitle.text.toString(), binding.etDescription.text.toString())
                viewModel.addNewNote(note)
                findNavController().navigate(R.id.action_AddNote_to_ToDoList)
                return true
            }
         }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}