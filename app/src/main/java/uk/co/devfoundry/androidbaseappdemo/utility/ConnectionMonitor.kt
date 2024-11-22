package uk.co.devfoundry.androidbaseappdemo.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.compose.foundation.content.MediaType.Companion.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun NetworkStatusChecker(context: Context) {

    //saving network state
    var isConnected by remember { mutableStateOf(true) }

    //Disposable for handling registeration/unreg
    DisposableEffect(context) {

        val connectionManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        //Building our checker or internet capability
        val networkRequest =
            NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

        //Used to obtain on/off functionality
        //In the body of this block, we can change state; based on trigger
        val callback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                isConnected = true
            }

            override fun onUnavailable() {
                super.onUnavailable()
                isConnected = false
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                isConnected = false
            }
        }

        //We'll need to reg/unreg our receiver
        connectionManager.registerNetworkCallback(networkRequest, callback)

        onDispose { connectionManager.unregisterNetworkCallback(callback) }

    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = if (isConnected) "Internet Available" else "Internet Unavailable")
        }
    }
}

@Preview
@Composable
fun PrevNetwork(modifier: Modifier = Modifier) {

}