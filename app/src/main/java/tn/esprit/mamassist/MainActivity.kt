package tn.esprit.mamassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.BottomNavigation.HomeScreen
import tn.esprit.mamassist.BottomNavigation.ProfileScreen
import tn.esprit.mamassist.Login.LoginScreen
import tn.esprit.mamassist.Login.LoginViewModel
import tn.esprit.mamassist.Pregnant.PregnantFormScreen
import tn.esprit.mamassist.Register.RegisterScreen
import tn.esprit.mamassist.data.network.ApiClient
import tn.esprit.mamassist.data.network.Baby
import tn.esprit.mamassist.data.repository.UserRepository
import tn.esprit.mamassist.ui.theme.MamAssistTheme

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
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            val apiService = ApiClient.getApiService()
            val userRepository = UserRepository(apiService)
            val loginViewModel = LoginViewModel(userRepository)
            LoginScreen(
                viewModel = loginViewModel,
                onNavigateToRegister = { navController.navigate("register") },
                onLoginSuccess = { navController.navigate("home") }
            )
        }
        composable("register") {
            RegisterScreen(
                onNavigateToLogin = { navController.navigate("login") }
            )
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("tools") {
            ToolsScreen(navController = navController)
        }
        composable("profile") {
            ProfileScreen()
        }
        composable("yourSituation") {
            YourSituationScreen(
                onPregnantClick = { navController.navigate("pregnantForm") },
                onAlreadyHaveBabiesClick = { navController.navigate("motherForm") }
            )
        }
        composable("pregnantForm") {
            PregnantFormScreen(
                userName = "Marie Dupont",
                userAge = "29",
                onSubmit = { formData ->
                    println("Formulaire soumis : $formData")
                }
            )
        }
        composable("motherForm") {
            MotherForm(
                onAddBabyClick = { navController.navigate("babyForm") }
            )
        }
        composable("babyForm") {
            BabyForm(
                baby = Baby(),
                onDelete = { navController.popBackStack() }
            )
        }
        composable("dailyCheck") {
            DailyCheckInScreen() // Ajoutez cette ligne
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MamAssistTheme {
        MainApp()
    }
}
