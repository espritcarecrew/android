package tn.esprit.mamassist

import MainInterface
import SharedPreferencesManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.Authentification.InscrireScreenWithPager
import tn.esprit.mamassist.BottomNavigation.HomeScreen
import tn.esprit.mamassist.BottomNavigation.ProfileScreen
import tn.esprit.mamassist.Authentification.connecte.LoginScreen
import tn.esprit.mamassist.Authentification.connecte.LoginViewModel
import tn.esprit.mamassist.MamaAvecBebe.BabyForm
import tn.esprit.mamassist.MamaAvecBebe.MotherForm
import tn.esprit.mamassist.Pregnant.PregnantFormScreen
import tn.esprit.mamassist.Tools.DailyCheckInScreen
import tn.esprit.mamassist.Authentification.inscrire.RegisterScreen
import tn.esprit.mamassist.Authentification.inscrire.RegisterViewModel
import tn.esprit.mamassist.Home.Chat.AskQuestionScreen
import tn.esprit.mamassist.Home.pression.BloodPressureInstructionsScreen
import tn.esprit.mamassist.Home.Chat.MedicalHelpScreen
import tn.esprit.mamassist.Home.WeightTrackerScreen
import tn.esprit.mamassist.Home.pression.PredictionScreen
import tn.esprit.mamassist.Pregnant.BabyGrowthScreen
import tn.esprit.mamassist.Pregnant.PregnancyToolsScreen
import tn.esprit.mamassist.Pregnant.PregnancyTrackerScreen
import tn.esprit.mamassist.Pregnant.PregnancyTrackingScreen
import tn.esprit.mamassist.Pregnant.SymptomsScreen
import tn.esprit.mamassist.Tools.TaskDetailsScreen
import tn.esprit.mamassist.Tools.ToolsScreen
import tn.esprit.mamassist.data.network.ApiClient
import tn.esprit.mamassist.data.network.Baby
import tn.esprit.mamassist.data.repository.UserRepository
import tn.esprit.mamassist.doctor.DoctorProfileScreen
import tn.esprit.mamassist.doctor.HealthAppHomeScreen
import tn.esprit.mamassist.screens.ArticleDetailScreen
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
    val apiService = ApiClient.apiService
    val userRepository = UserRepository(apiService)
    val registerViewModel = RegisterViewModel(userRepository)

    NavHost(navController = navController, startDestination = "inscrire") {
        composable("inscrire") {
            InscrireScreenWithPager(navController = navController)
        }
        composable("login") {
            val loginViewModel = LoginViewModel(userRepository)
            val context = LocalContext.current

            LoginScreen(
                viewModel = loginViewModel,
                onNavigateToRegister = { navController.navigate("register") },
                onLoginSuccess = { userId ->
                    SharedPreferencesManager(context).saveUserId(userId) // Sauvegarde l'userId
                    navController.navigate("maininterface") // Navigation après succès
                }
            )
        }

        composable("askQuestion") {
            AskQuestionScreen()
        }

        composable("PredictionScreen") {
            PredictionScreen()
        }
        composable("articleDetail/{articleIndex}") { backStackEntry ->
            val articleIndex = backStackEntry.arguments?.getString("articleIndex")?.toIntOrNull() ?: 0
            ArticleDetailScreen(navController = navController, articleIndex = articleIndex)
        }
        composable("register") {
            RegisterScreen(
                viewModel = registerViewModel,
                onNavigateToLogin = { navController.navigate("inscrire") } // Navigation vers root
            )
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("tools") {
            ToolsScreen(navController = navController)
        }

        composable("profile") {
            ProfileScreen(navController = navController)
        }
        composable("doctor") {
            HealthAppHomeScreen(navController = navController)
        }
        composable("discussions") {
            MedicalHelpScreen(navController = navController)
        }
        composable("PregnancyTrackerScreen") {
            PregnancyTrackerScreen(navController = navController)
        }
        composable("yourSituation") {
            YourSituationScreen(
                onPregnantClick = { navController.navigate("pregnantForm") },
                onAlreadyHaveBabiesClick = { navController.navigate("motherForm") },
                onBackClick = { navController.navigate("home") }
            )
        }
        composable("pregnancyTracking") {
            PregnancyTrackingScreen(navController = navController)
        }
        composable("PregnancyToolsScreen") {
            PregnancyToolsScreen(navController = navController)
        }
        composable("pregnantForm") {
            PregnantFormScreen(
                userName = null,
                userAge = null,
                onSubmit = { /* Action après soumission */ },
                navController = navController
            )
        }

        composable("motherForm") {
            MotherForm(
                navController = navController,
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
            DailyCheckInScreen(navController = navController)
        }
        composable("todo") {
            ToDoScreen(navController = navController)
        }
        composable("taskDetails") {
            TaskDetailsScreen(navController = navController)
        }
        composable("healthAppHome") {
            HealthAppHomeScreen(navController = navController)
        }
        composable("doctorProfile") {
            DoctorProfileScreen(navController = navController)
        }
        composable("maininterface") {
             MainInterface(navController = navController)
        }
        composable("BloodPressureInstructionsScreen") {
            BloodPressureInstructionsScreen(navController = navController)
        }
        composable("SymptomsScreen") {
            SymptomsScreen(navController = navController)
        }
        composable("medicalHelpScreen") { MedicalHelpScreen(navController = navController) }
        composable("articleDetail/{articleIndex}") { backStackEntry ->
            val articleIndex = backStackEntry.arguments?.getString("articleIndex")?.toInt() ?: 0
            ArticleDetailScreen(navController = navController, articleIndex = articleIndex)
        }

        composable("WeightTrackerScreen") { WeightTrackerScreen(navController = navController) }
        composable("BabyGrowthScreen") { BabyGrowthScreen(navController = navController) }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MamAssistTheme {
        MainApp()
    }
}
