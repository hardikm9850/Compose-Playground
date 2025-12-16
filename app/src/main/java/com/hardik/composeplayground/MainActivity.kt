package com.hardik.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hardik.core.navigation.NavigationHost
import com.hardik.core.navigation.navigationItems
import com.hardik.core.theme.ComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposePlaygroundTheme {
                CreateView()
            }
        }
    }

    @Composable
    private fun CreateView() {
        val navController = rememberNavController()

        Scaffold(
            bottomBar = {
                BottomNavBar(navController)
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            NavigationHost(innerPadding, navController)
        }
    }

    @Composable
    fun BottomNavBar(navController: NavHostController) {
        var selectedIndex by remember { mutableIntStateOf(0) }

        NavigationBar {
            navigationItems.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = { Icon(item.screen.icon, contentDescription = item.screen.name) },
                    label = { Text(item.screen.name) },
                    selected = selectedIndex == index,
                    onClick = {
                        selectedIndex = index
                        navController.navigate(item.screen.name)
                    }
                )
            }
        }
    }
}
