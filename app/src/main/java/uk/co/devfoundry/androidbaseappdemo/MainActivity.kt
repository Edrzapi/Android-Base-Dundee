package uk.co.devfoundry.androidbaseappdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import uk.co.devfoundry.androidbaseappdemo.ui.theme.AndroidBaseAppDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            AndroidBaseAppDemoTheme {
                AppNav(navController)
            }
        }
    }
}

@Composable
fun SetUpFunction(navController: NavController? = null) {
    MyTopBar(navController)
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    )
    {
        Spacer(modifier = Modifier.padding(10.dp))
        Image(
            painter = painterResource(R.drawable.sheep),
            contentDescription = "sheep!",
            Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text(text = "Weclome to my application..")
        Button(onClick = {
        }) {
            Text("Click")
        }
    }
}

@Preview
@Composable
fun PrevSetUpFunction() {
    SetUpFunction()
}