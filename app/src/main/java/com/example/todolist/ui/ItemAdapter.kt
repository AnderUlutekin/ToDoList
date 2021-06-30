package com.example.todolist.ui

import android.graphics.Paint
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Item
import com.example.todolist.databinding.SingleItemBinding

class ItemAdapter : ListAdapter<Item, ItemAdapter.ViewHolder>(ItemDiffCallback) {

    companion object ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
    }

    class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val checkBox = binding.cbStatus
        var text = binding.tvItem
        init {
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    text.paintFlags = text.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                }
                else {
                    text.paintFlags = 0
                }
            }
        }

        fun bind(item: Item) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }


}