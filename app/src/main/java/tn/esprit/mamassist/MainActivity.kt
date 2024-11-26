package tn.esprit.mamassist

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
        composable("profile") {
            ProfileScreen(navController = navController) // Add the profile screen route
        }
    }
}
