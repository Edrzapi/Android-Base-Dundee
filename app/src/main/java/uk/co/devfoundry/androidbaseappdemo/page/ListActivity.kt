package uk.co.devfoundry.androidbaseappdemo.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import uk.co.devfoundry.androidbaseappdemo.MyTopBar
import uk.co.devfoundry.androidbaseappdemo.ui.theme.AndroidBaseAppDemoTheme
import uk.co.devfoundry.androidbaseappdemo.viewmodel.ListViewModel

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidBaseAppDemoTheme {
                ListActivitySetup()
            }
        }
    }
}

@Composable
fun ListActivitySetup(
    navController: NavController? = null,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        MyTopBar(navController)
        Column(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ListContainer()
        }
    }
}

@Composable
fun ListContainer(viewModel: ListViewModel = viewModel()) {

    var inputText by remember { mutableStateOf("") }

    Row(verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            modifier = Modifier.padding(15.dp)
        )
        Button(onClick = { viewModel.addItem(null, inputText) }) {
            Text("Add")
        }
    }
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        itemsIndexed(viewModel.listItems) { index, item ->
            Row(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = item)
                Button(onClick = { viewModel.removeItem(index) }) { Text(text = "Delete") }
            }
        }
    }
}






































