package tn.esprit.mamassist.BottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Tools : BottomBarScreen(
        route = "Tools",
        title = "Tolls",
        icon = Icons.Default.Build
    )
    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Settings : BottomBarScreen(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )


}
