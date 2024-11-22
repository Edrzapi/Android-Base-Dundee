package uk.co.devfoundry.androidbaseappdemo.page

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import uk.co.devfoundry.androidbaseappdemo.MyTopBar
import uk.co.devfoundry.androidbaseappdemo.data.api.ApiService
import uk.co.devfoundry.androidbaseappdemo.data.api.RetrofitObject
import uk.co.devfoundry.androidbaseappdemo.model.Pokemon
import uk.co.devfoundry.androidbaseappdemo.page.ui.theme.AndroidBaseAppDemoTheme

class ApiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidBaseAppDemoTheme {
                SetupPokeListFunction()
            }
        }
    }
}

@Composable
fun SetupPokeListFunction(navController: NavController? = null) {
    Box(modifier = Modifier.fillMaxSize()) {
        MyTopBar(navController)
        Column(
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PokeListContainer()
        }
    }
}


@Composable
fun PokeListContainer() {
    var pokeList by remember { mutableStateOf<List<Pokemon>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    //Fetch our data (pokemon)
    LaunchedEffect(Unit) {
        val apiService = RetrofitObject.instance.create(ApiService::class.java)
        try {
            val response = apiService.getPokemonList()
            pokeList = response.results.take(20)

        } catch (e: Exception) {
            println(e.message)
        } finally {
            isLoading = false
        }
    }
    //setup UI
    if (isLoading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        LazyColumn {
            items(pokeList) { pokemon ->
                Text(pokemon.name)
            }
        }
    }


}


@Preview
@Composable
fun PrevPokeListContainer() {
    PrevPokeListContainer()
}
