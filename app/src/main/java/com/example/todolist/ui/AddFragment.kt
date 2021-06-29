package com.example.todolist.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.data.Item
import com.example.todolist.databinding.FragmentAddBinding
import com.example.todolist.ui.viewmodel.ItemViewModel
import java.util.*

class AddFragment : Fragment() {

    private val viewModel: ItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddBinding.inflate(inflater)

        binding.fabComplete.setOnClickListener {
            if (TextUtils.isEmpty((binding.etItem.text))) {
                Toast.makeText(requireContext(), "Boş bırakmayın!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val message = binding.etItem.text.toString()
            val completed = false
            val cal = Calendar.getInstance()
            cal.set(Calendar.DAY_OF_MONTH, binding.dpDate.dayOfMonth)
            cal.set(Calendar.MONTH, binding.dpDate.month)
            cal.set(Calendar.YEAR, binding.dpDate.year)

            val timeInMiliseconds = cal.timeInMillis

            val item = Item(
                message,
                completed,
                timeInMiliseconds,
                0
            )

            viewModel.insert(item)
            Toast.makeText(requireContext(), "Eklendi", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment2_to_homeFragment2)
        }

        return binding.root
    }
}