package uk.co.devfoundry.androidbaseappdemo.page

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.ComponentActivity
import uk.co.devfoundry.androidbaseappdemo.R

class IntentActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intent_activity)

        val sendButton: Button = findViewById(R.id.send)
        sendButton.setOnClickListener { sendEmailTo() }
    }

    fun sendEmailTo() {
        val recipient = "example@example.com"
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$recipient")
            putExtra(Intent.EXTRA_SUBJECT, "subject of email..")
            putExtra(Intent.EXTRA_TEXT, "Some text")
        }

        if (emailIntent.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(emailIntent, "Send mail to.."))
        } else {
            Toast.makeText(this, "You have an error!", LENGTH_LONG).show()
        }
    }
}