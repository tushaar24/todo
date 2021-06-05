package com.example.todolist.fragment.todolist

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.data.entity.Note
import com.example.todolist.databinding.FragmentTodolistBinding
import com.example.todolist.fragment.addnote.AddNoteViewModel

class TodoList : Fragment() , SearchView.OnQueryTextListener {

    private var _binding: FragmentTodolistBinding? = null
    lateinit  var mAdapter : ToDoListAdapter
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
            findNavController().navigate(R.id.action_ToDoList_to_AddNote)
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun setUpRecyclerViewAdapter() {
        mAdapter = ToDoListAdapter(onClick)
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

     private val onClick : (note : Note) -> Unit = {
        val action : NavDirections = TodoListDirections.actionToDoListToModifyNote(it)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list_note_delete_all, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete_all ->{
                viewModel.deleteAllNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null){
            val searchQuery = "%$query%"
            viewModel.searchNote(searchQuery).observe(viewLifecycleOwner, {
                mAdapter.setData(it)
            })
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null){
            val searchQuery = "%$newText%"
            viewModel.searchNote(searchQuery).observe(viewLifecycleOwner, {
                mAdapter.setData(it)
            })
        }
        return true
    }
}