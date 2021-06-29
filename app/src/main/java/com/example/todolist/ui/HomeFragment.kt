package com.example.todolist.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.FragmentHomeBinding
import com.example.todolist.ui.viewmodel.ItemViewModel
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private val viewModel: ItemViewModel by viewModels()
    private lateinit var adapter: ItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        adapter = ItemAdapter()

        viewModel.getAllItems.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.rvList.adapter = adapter

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment2_to_addFragment2)
        }

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val item = adapter.currentList[position]
                viewModel.delete(item)

                Snackbar.make(binding.root, "Silindi!", Snackbar.LENGTH_LONG).apply {
                    setAction("Geri") {
                        viewModel.insert(item)
                    }
                    show()
                }
            }
        }).attachToRecyclerView(binding.rvList)

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.item_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_delete_all -> deleteAllItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllItem() {
        AlertDialog.Builder(requireContext())
            .setTitle("Hepsini Sil")
            .setMessage("Emin misiniz?")
            .setPositiveButton("Evet"){dialog, _ ->
                viewModel.deleteAll()
                dialog.dismiss()
            }.setNegativeButton("Hayı"){dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }
}