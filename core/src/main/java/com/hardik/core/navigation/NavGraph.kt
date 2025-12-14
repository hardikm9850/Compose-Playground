package com.hardik.core.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hardik.feature_todo.ToDoScreen

@Composable
fun NavigationHost(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Navigation.ToDo.name,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(Navigation.ToDo.name) { ToDoScreen() }
        composable(Navigation.Chat.name) { ToDoScreen() }
        composable(Navigation.Feed.name) { ToDoScreen() }
        composable(Navigation.Music.name) { ToDoScreen() }
        composable(Navigation.Settings.name) { ToDoScreen() }
    }
}