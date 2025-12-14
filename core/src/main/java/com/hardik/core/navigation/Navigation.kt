package com.hardik.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val screen: Navigation
)

sealed class Navigation(val name: String, val icon: ImageVector) {
    object ToDo : Navigation("Todo", Icons.Filled.Check)
    object Chat : Navigation("Chat", Icons.Filled.Person)
    object Feed : Navigation("Feed", Icons.Filled.Face)
    object Music : Navigation("Music", Icons.Filled.PlayArrow)
    object Settings : Navigation("Settings", Icons.Filled.Settings)
}

val navigationItems = listOf(
    NavItem(Navigation.ToDo),
    NavItem(Navigation.Chat),
    NavItem(Navigation.Feed),
    NavItem(Navigation.Music),
    NavItem(Navigation.Settings)
)

