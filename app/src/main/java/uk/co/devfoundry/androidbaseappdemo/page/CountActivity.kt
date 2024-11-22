package uk.co.devfoundry.androidbaseappdemo.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import uk.co.devfoundry.androidbaseappdemo.MyTopBar
import uk.co.devfoundry.androidbaseappdemo.ui.theme.AndroidBaseAppDemoTheme
import uk.co.devfoundry.androidbaseappdemo.viewmodel.CounterViewModel

class CountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidBaseAppDemoTheme {
                CountActivitySetup()
            }

        }
    }
}

@Composable
fun CountActivitySetup(
    navController: NavController? = null,
    viewModel: CounterViewModel = viewModel()
) {
    MyTopBar(navController)
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { viewModel.incrementCounter() }) { Text("Count") }
        Text("${viewModel.counter}")
    }
}
