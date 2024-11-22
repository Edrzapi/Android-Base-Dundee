package uk.co.devfoundry.androidbaseappdemo.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import uk.co.devfoundry.androidbaseappdemo.MyTopBar
import uk.co.devfoundry.androidbaseappdemo.page.ui.theme.AndroidBaseAppDemoTheme
import uk.co.devfoundry.androidbaseappdemo.viewmodel.CallLogViewModel

class CallLogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidBaseAppDemoTheme {
                SetupCallLogViewer()
            }
        }
    }
}


@Composable
fun SetupCallLogViewer(navController: NavController? = null) {
    Box(modifier = Modifier.fillMaxSize()) {
        MyTopBar(navController)
        Column(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CallLogContainer()
        }
    }
}

@Composable
fun CallLogContainer(viewModel: CallLogViewModel = viewModel()) {
    val callList by viewModel.callList.collectAsState()

    viewModel.loadData(LocalContext.current.contentResolver)
    // Use a Column to arrange LazyColumn and Button vertically
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp)
    ) {
        LazyColumn(modifier = Modifier.padding(15.dp)) {
            items(callList) { call ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = "Number: ${call.number}")
                    Text(text = "Date: ${call.date}")

                    HorizontalDivider() /* Adds a divider between items */
                }
            }
        }
    }
}