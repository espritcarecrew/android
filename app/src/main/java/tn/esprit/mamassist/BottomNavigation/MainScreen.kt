package tn.esprit.mamassist.BottomNavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.ID.ViewModelFactory
import tn.esprit.mamassist.LoginScreen
import tn.esprit.mamassist.LoginViewModel
import tn.esprit.mamassist.RegisterScreen
import tn.esprit.mamassist.data.network.ApiClient
import tn.esprit.mamassist.data.repository.UserRepository
import tn.esprit.mamassist.screens.HomeScreen
import tn.esprit.mamassist.screens.ProfileScreen
import tn.esprit.mamassist.ui.theme.MamAssistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MamAssistTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    // Initialize dependencies
    val apiService = ApiClient.getApiService()
    val userRepository = UserRepository(apiService)

    // Use ViewModelFactory to instantiate LoginViewModel
    val loginViewModel: LoginViewModel = viewModel(
        factory = ViewModelFactory.getInstance(userRepository, apiService)
    )

    // Define navigation destinations
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                viewModel = loginViewModel,
                onNavigateToRegister = { navController.navigate("register") },
                onLoginSuccess = { navController.navigate("home") }
            )
        }
        composable("register") {
            RegisterScreen(onNavigateToLogin = { navController.navigate("login") })
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("profile") {
            ProfileScreen(navController = navController)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MamAssistTheme {
        MainScreen() // Preview of the main app
    }
}
