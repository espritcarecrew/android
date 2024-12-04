package tn.esprit.mamassist

import CalendarScreen
//import MotherAndBabyScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.LoginScreen
import tn.esprit.mamassist.RegisterScreen
import tn.esprit.mamassist.screens.HomeScreen
import tn.esprit.mamassist.screens.ProfileScreen
import tn.esprit.mamassist.ui.theme.MamAssistTheme
import tn.esprit.mamassist.data.network.ApiClient
import tn.esprit.mamassist.data.repository.UserRepository
import tn.esprit.mamassist.screens.ArticleDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MamAssistTheme {
               MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController() // The NavController is now here

    // Set up NavHost with all your routes
    NavHost(navController = navController, startDestination = "login") {
        composable("MotherAndBabyScreen") {
            MotherAndBabyScreen(navController = navController) // Pass the NavController here
        }
        composable("login") {
            val apiService = ApiClient.getApiService()
            val userRepository = UserRepository(apiService)
            val loginViewModel = LoginViewModel(userRepository, apiService)
            LoginScreen(
                viewModel = loginViewModel,
                onNavigateToRegister = { navController.navigate("register") },
                onLoginSuccess = { navController.navigate("home/pregnant") } // Navigate to home with "pregnant" as an example userType
            )
        }
        composable("register") {
            RegisterScreen(onNavigateToLogin = { navController.navigate("login") })
        }
        composable("home/pregnant") { backStackEntry ->
           // val userType = backStackEntry.arguments?.getString("userType") ?: "default"
            HomeScreen(navController = navController) // Pass the userType to HomeScreen
        }

        composable("articleDetail/{articleIndex}") { backStackEntry ->
            val articleIndex = backStackEntry.arguments?.getString("articleIndex")?.toInt() ?: 0
            ArticleDetailScreen(navController = navController, articleIndex = articleIndex)
        }
        composable("profile") {
            ProfileScreen(navController = navController) // Add the profile screen route
        }
        composable("calendar") {
            CalendarScreen(navController = navController) // Add the profile screen route
        }
        composable("babyList") {
            BabyListScreen(navController = navController)
        }
        composable("motherList") {
            MotherListScreen(navController = navController)
        }

    }
}
