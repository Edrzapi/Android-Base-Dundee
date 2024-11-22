package uk.co.devfoundry.androidbaseappdemo.page

import android.Manifest
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uk.co.devfoundry.androidbaseappdemo.MyTopBar
import uk.co.devfoundry.androidbaseappdemo.ui.theme.AndroidBaseAppDemoTheme
import uk.co.devfoundry.androidbaseappdemo.utility.requestPerms

class BroadcastReceiverActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidBaseAppDemoTheme {
                BroadcastReceiverSetup()
            }
        }
    }
}


@Composable
fun BroadcastReceiverSetup(navController: NavController? = null) {
    MyTopBar(navController)
    Box(modifier = Modifier.fillMaxSize()) {
        MyTopBar(navController)
        Column(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BroadcastReceiverDemo()
        }
    }
}

@Composable
fun BroadcastReceiverDemo() {

    //Save state of APM
    var airplaneModeState by remember { mutableStateOf("Trigger APM") }

    // Set context in compose
    val context = LocalContext.current
    //Register the BR, with cleanup

    requestPerms(context as Activity, Manifest.permission.CAMERA, 1)

    DisposableEffect(Unit) {
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == Intent.ACTION_AIRPLANE_MODE_CHANGED) {
                    val isAirplaneModeOn = intent.getBooleanExtra("state", false)
                    airplaneModeState = if (isAirplaneModeOn) {
                        "Airplane mode on"
                    } else {
                        "Airplane mode off"
                    }
                }
            }
        }
        // Access
        val intentFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        context.registerReceiver(receiver, intentFilter)

        // Clean up
        onDispose { context.unregisterReceiver(receiver) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) { Text("$airplaneModeState") }
    }

}

@Preview
@Composable
fun PrevBR(modifier: Modifier = Modifier) {
    BroadcastReceiverDemo()

}