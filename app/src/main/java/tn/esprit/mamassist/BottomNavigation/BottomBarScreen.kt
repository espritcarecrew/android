package tn.esprit.mamassist.BottomNavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home // Icône pour la maison
    )

    object Tools : BottomBarScreen(
        route = "tools",
        title = "Tools",
        icon = Icons.Default.Build // Icône pour les outils
    )

    object Discussions : BottomBarScreen(
        route = "discussions",
        title = "Discussions",
        icon = Icons.Default.Call // Icône pour les discussions (chat)
    )

    object Doctor : BottomBarScreen(
        route = "doctor",
        title = "Doctor",
        icon = Icons.Default.ThumbUp // Icône pour les docteurs ou services médicaux
    )
}
