package uk.co.devfoundry.androidbaseappdemo.contentprovider

import android.content.ContentResolver
import android.database.Cursor
import android.provider.CallLog
import java.util.Date

class CallLogProvider {

    fun fetchHistory(contentResolver: ContentResolver): List<CallLogData> {

        val projection = arrayOf(
            CallLog.Calls._ID,
            CallLog.Calls.NUMBER,
            CallLog.Calls.TYPE,
            CallLog.Calls.DATE,
        )

        val cursor: Cursor? = contentResolver.query(
            CallLog.Calls.CONTENT_URI,
            projection,
            null,
            null,
            "${CallLog.Calls.DATE} DESC"
        )

        //storage
        val callLogEntries = mutableListOf<CallLogData>()

        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getLong(it.getColumnIndexOrThrow(CallLog.Calls._ID))
                val number = it.getString(it.getColumnIndexOrThrow(CallLog.Calls.NUMBER))
                val type = it.getInt(it.getColumnIndexOrThrow(CallLog.Calls.TYPE))
                val date = it.getLong(it.getColumnIndexOrThrow(CallLog.Calls.DATE))
                // conversion..
                val dateFormat = Date(date)
                val typeFormat = when (type) {
                    CallLog.Calls.INCOMING_TYPE -> "Incoming"
                    CallLog.Calls.OUTGOING_TYPE -> "Outgoing"
                    CallLog.Calls.MISSED_TYPE -> "Missed"
                    else -> {
                        "Other.."
                    }
                }

                callLogEntries.add(CallLogData(id, number, typeFormat, dateFormat.toString()))
            }
        }
        return callLogEntries
    }
}

data class CallLogData(
    val id: Long,
    val number: String,
    val type: String,
    val date: String
)
