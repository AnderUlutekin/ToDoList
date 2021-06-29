package com.example.todolist.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.Item
import com.example.todolist.data.ItemDatabase
import com.example.todolist.repositories.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application) : AndroidViewModel(application) {

    private val listDao = ItemDatabase.getDatabase(application).listDao()
    private val repository: ItemRepository

    val getAllItems: LiveData<List<Item>>
    init {
        repository = ItemRepository(listDao)
        getAllItems = repository.getAllItems()
    }

    fun insert(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(item)
        }
    }
    fun delete(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(item)
        }
    }

    fun update(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(item)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
}