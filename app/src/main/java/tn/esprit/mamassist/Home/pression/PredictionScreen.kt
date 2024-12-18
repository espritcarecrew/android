package tn.esprit.mamassist.Home.pression

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import tn.esprit.mamassist.data.network.PredictionRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PredictionScreen(viewModel: PredictionViewModel = viewModel(), navController: NavController? = null) {
    var age by remember { mutableStateOf("") }
    var systolicBP by remember { mutableStateOf("") }
    var diastolicBP by remember { mutableStateOf("") }
    var bs by remember { mutableStateOf("") }
    var bodyTemp by remember { mutableStateOf("") }
    var heartRate by remember { mutableStateOf("") }

    val predictionResult by viewModel.predictionResult.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController?.navigate("BloodPressureInstructionsScreen") }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Quelle est votre HealthPrediction ?",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFFFF6961),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Champs de saisie
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Âge") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = systolicBP,
                onValueChange = { systolicBP = it },
                label = { Text("Pression Systolique") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = diastolicBP,
                onValueChange = { diastolicBP = it },
                label = { Text("Pression Diastolique") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = bs,
                onValueChange = { bs = it },
                label = { Text("BS") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = bodyTemp,
                onValueChange = { bodyTemp = it },
                label = { Text("Température Corporelle") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = heartRate,
                onValueChange = { heartRate = it },
                label = { Text("Fréquence Cardiaque") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Bouton pour envoyer les données
            Button(onClick = {
                viewModel.getPrediction(
                    PredictionRequest(
                        age = age.toIntOrNull() ?: 0,
                        systolicBP = systolicBP.toFloatOrNull() ?: 0f,
                        diastolicBP = diastolicBP.toFloatOrNull() ?: 0f,
                        bs = bs.toFloatOrNull() ?: 0f,
                        bodyTemp = bodyTemp.toFloatOrNull() ?: 0f,
                        heartRate = heartRate.toIntOrNull() ?: 0
                    )
                )
            }) {
                Text("Envoyer")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Affichage du résultat de la prédiction
            predictionResult?.let {
                Text(
                    text = "Résultat : $it",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
            } ?: Text(
                text = "Aucun résultat pour l'instant",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPredictionScreen() {
    PredictionScreen()
}
