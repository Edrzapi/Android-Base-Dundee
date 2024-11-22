package uk.co.devfoundry.androidbaseappdemo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import uk.co.devfoundry.androidbaseappdemo.page.BroadcastReceiverSetup
import uk.co.devfoundry.androidbaseappdemo.page.CountActivitySetup
import uk.co.devfoundry.androidbaseappdemo.page.ListActivitySetup
import uk.co.devfoundry.androidbaseappdemo.page.SetupCallLogViewer
import uk.co.devfoundry.androidbaseappdemo.page.SetupPokeListFunction


@Composable
fun AppNav(navController: NavHostController) {
    NavHost(navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) { SetUpFunction(navController) }
        composable(Routes.LIST) { ListActivitySetup(navController) }
        composable(Routes.COUNT) { CountActivitySetup(navController) }
        composable(Routes.BR) { BroadcastReceiverSetup(navController) }
        composable(Routes.CALLLOG) {
            SetupCallLogViewer(navController)
        }
        composable(Routes.API) { SetupPokeListFunction(navController) }
    }
}


object Routes {
    const val HOME = "Home"
    const val LIST = "List Activity"
    const val COUNT = "Count Activity"
    const val BR = "BR"
    const val CALLLOG = "CL"
    const val API = "api"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavController? = null) {
    val expanded = remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text("App") },
        navigationIcon = {
            IconButton(onClick = { expanded.value = !expanded.value }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }) {
                DropdownMenuItem(
                    text = { Text("Home") },
                    leadingIcon = {
                        Icon(Icons.Default.Home, contentDescription = "Home")
                    },
                    onClick = {
                        navController?.navigate(Routes.HOME)
                        expanded.value = false
                    },
                )
                DropdownMenuItem(
                    text = { Text("List Task") }, leadingIcon = {
                        Icon(Icons.Default.List, contentDescription = "List Task")
                    },
                    onClick = {
                        navController?.navigate(Routes.LIST)
                        expanded.value = false
                    })
                DropdownMenuItem(
                    text = { Text("Count Task") }, leadingIcon = {
                        Icon(Icons.Default.Check, contentDescription = "Count Task")
                    }, onClick = {
                        navController?.navigate(Routes.COUNT)
                        expanded.value = false
                    })
                DropdownMenuItem(
                    text = { Text("BR Task") }, leadingIcon = {
                        Icon(Icons.Default.Info, contentDescription = "BR Task")
                    }, onClick = {
                        navController?.navigate(Routes.BR)
                        expanded.value = false
                    })
                DropdownMenuItem(
                    text = { Text("CL Task") }, leadingIcon = {
                        Icon(Icons.Default.Call, contentDescription = "CL Task")
                    }, onClick = {
                        navController?.navigate(Routes.CALLLOG)
                        expanded.value = false
                    })
                DropdownMenuItem(
                    text = { Text("API Task") }, leadingIcon = {
                        Icon(Icons.Default.Build, contentDescription = "API Task")
                    }, onClick = {
                        navController?.navigate(Routes.API)
                        expanded.value = false
                    })
            }
        }
    )
}

