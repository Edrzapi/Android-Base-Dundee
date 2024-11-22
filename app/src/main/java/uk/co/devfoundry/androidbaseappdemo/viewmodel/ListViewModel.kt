package uk.co.devfoundry.androidbaseappdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf

class ListViewModel : ViewModel() {

    private val _listItems = mutableStateListOf<String>()
    val listItems: List<String> = _listItems

    fun addItem(index: Int?, newItem: String) {
        if (index != null && index in _listItems.indices) {
            _listItems[index] = newItem
        } else {
            _listItems.add(newItem)
        }
    }

    fun removeItem(index: Int) {
        if (index in _listItems.indices) {
            _listItems.removeAt(index)
        }
    }
}


