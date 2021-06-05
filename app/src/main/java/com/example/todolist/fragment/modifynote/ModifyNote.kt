package com.example.todolist.fragment.modifynote

import android.os.Bundle
import android.provider.ContactsContract
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todolist.R
import com.example.todolist.data.entity.Note
import com.example.todolist.databinding.FragmentModifyNoteBinding
import com.example.todolist.fragment.addnote.AddNoteViewModel

class ModifyNote : Fragment() {

    private var _binding : FragmentModifyNoteBinding? = null
    private val viewModel by viewModels<AddNoteViewModel>()

    private val binding get() = _binding!!

    private val args by navArgs<ModifyNoteArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentModifyNoteBinding.inflate(inflater, container, false)
        binding.etTitle.setText(args.currentNote.title)
        binding.etDescription.setText(args.currentNote.decripion)
        binding.etTitle.requestFocus()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_update, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_update ->{
                val title = binding.etTitle.text.toString()
                val description = binding.etDescription.text.toString()
                viewModel.updateNote(Note(title, description, args.currentNote.id))
                findNavController().navigate(R.id.action_modifyNote_to_ToDoList)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}