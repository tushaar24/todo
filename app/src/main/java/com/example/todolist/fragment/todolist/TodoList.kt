package com.example.todolist.fragment.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.FragmentTodolistBinding

class TodoList : Fragment() {

    private var _binding: FragmentTodolistBinding? = null
    private val mAdapter by lazy { ToDoListAdapter() }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTodolistBinding.inflate(inflater, container, false)
        setUpRecyclerViewAdapter()
        return binding.root
    }

    private fun setUpRecyclerViewAdapter() {
        binding.rvTodoList.adapter = mAdapter
        binding.rvTodoList.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}