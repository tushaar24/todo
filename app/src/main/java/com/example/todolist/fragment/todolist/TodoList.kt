package com.example.todolist.fragment.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.FragmentTodolistBinding
import com.example.todolist.fragment.addnote.AddNoteViewModel

class TodoList : Fragment() {

    private var _binding: FragmentTodolistBinding? = null
    private val mAdapter by lazy { ToDoListAdapter() }
    private val viewModel by lazy { AddNoteViewModel(requireActivity().application) }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTodolistBinding.inflate(inflater, container, false)
        setUpRecyclerViewAdapter()
        viewModel.noteList.observe(viewLifecycleOwner, {
            mAdapter.setData(it)
        })

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        return binding.root
    }

    private fun setUpRecyclerViewAdapter() {
        binding.rvTodoList.adapter = mAdapter
        binding.rvTodoList.layoutManager = LinearLayoutManager(requireContext())

        swipeToDelete(binding.rvTodoList)
    }

    private fun swipeToDelete(rvTodoList: RecyclerView) {
        val swipeToDelete = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deleteItem = mAdapter.notesList[viewHolder.adapterPosition]
                viewModel.deleteNote(deleteItem)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(rvTodoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}