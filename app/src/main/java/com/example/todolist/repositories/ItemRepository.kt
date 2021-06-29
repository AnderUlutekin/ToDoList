package com.example.todolist.repositories

import androidx.lifecycle.LiveData
import com.example.todolist.data.Item
import com.example.todolist.data.ListDao

class ItemRepository(val listDao: ListDao) {

    suspend fun insert(item: Item) = listDao.insert(item)

    suspend fun update(item: Item) = listDao.update(item)

    suspend fun delete(item: Item) = listDao.delete(item)

    suspend fun deleteAll() {
        listDao.deleteAll()
    }

    fun getAllItems() : LiveData<List<Item>> = listDao.getAllItems()

}