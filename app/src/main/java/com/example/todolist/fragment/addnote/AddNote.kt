package com.example.todolist.fragment.addnote

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.data.entity.Note
import com.example.todolist.databinding.FragmentAddNoteBinding
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.to_do_item.*

class AddNote : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val viewModel by lazy { AddNoteViewModel(requireActivity().application) }

    // This property is only valid between onCreateView and
    // onDestroyView.
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
                val note = Note(etTitle.text.toString(), etDescription.text.toString())
                viewModel.addNewNote(note)
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
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