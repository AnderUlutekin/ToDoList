package com.example.todolist

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DateFormat

@BindingAdapter("setDate")
fun setDate(view: TextView, date: Long) {
    view.text = DateFormat.getInstance().format(date)
}