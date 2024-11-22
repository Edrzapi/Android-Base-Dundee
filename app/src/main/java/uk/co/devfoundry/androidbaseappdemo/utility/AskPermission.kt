package uk.co.devfoundry.androidbaseappdemo.utility

import android.app.Activity
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


fun requestPerms(context: Activity, permission: String, requestCode: Int) {
    if (ContextCompat.checkSelfPermission(
            context, permission
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            context,
            arrayOf(permission),
            requestCode
        )
        Toast.makeText(context, "Permission requested", Toast.LENGTH_SHORT).show()

    } else {
        Toast.makeText(context, "Permission already granted!", Toast.LENGTH_SHORT).show()

    }
}

