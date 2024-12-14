package tn.esprit.mamassist.Home.pression

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun BloodPressureInputScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tension", style = MaterialTheme.typography.h6) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("BloodPressureInstructionsScreen")}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Quelle est votre tension artérielle actuelle ?",
                        style = MaterialTheme.typography.h6,
                        color = Color(0xFFFF6961),
                        modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Handle systolic value change */ },
                        label = { Text("Systolique (Grand nombre)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = "",
                        onValueChange = { /* Handle diastolic value change */ },
                        label = { Text("Diastolique (Petit nombre)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Button(
                    onClick = { /* Handle save action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6961))
                ) {
                    Text(text = "Enregistrer", color = Color.White)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun BloodPressureInputScreenPreview() {
    BloodPressureInputScreen(navController = rememberNavController())
}
