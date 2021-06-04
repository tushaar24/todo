package com.example.todolist.fragment.addnote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.let {
            btnAdd.setOnClickListener {
                    val note = Note(etTitle.text.toString(), etDescription.text.toString())
                    viewModel.addNewNote(note)
                    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}