package uk.co.devfoundry.androidbaseappdemo.viewmodel

import android.content.ContentResolver
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import uk.co.devfoundry.androidbaseappdemo.contentprovider.CallLogData
import uk.co.devfoundry.androidbaseappdemo.contentprovider.CallLogProvider

class CallLogViewModel : ViewModel() {

    private val _callList = MutableStateFlow<List<CallLogData>>(emptyList())
    val callList: StateFlow<List<CallLogData>> = _callList

    fun loadData(contentResolver: ContentResolver) {
        _callList.value = CallLogProvider().fetchHistory(contentResolver)
    }

}