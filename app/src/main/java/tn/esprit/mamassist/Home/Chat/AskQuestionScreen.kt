package tn.esprit.mamassist.Home.Chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
@Composable
fun AskQuestionScreen() {
    val viewModel: AskQuestionViewModel = viewModel()
    var userInput by remember { mutableStateOf("") }
    var responseMessage by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Poser votre question", fontSize = 24.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        // Champ de saisie
        OutlinedTextField(
            value = userInput,
            onValueChange = { userInput = it },
            label = { Text("Écrivez votre question ici...") },
            maxLines = 2,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bouton pour envoyer la question
        Button(onClick = {
            coroutineScope.launch {
                viewModel.sendQuestion(userInput) { response, cat ->
                    responseMessage = response
                    category = cat
                }
            }
        }) {
            Icon(Icons.Default.Send, contentDescription = "Send", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Envoyer", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Affichage de la réponse
        if (responseMessage.isNotEmpty()) {
            Text(text = "Réponse de l'IA :", fontSize = 18.sp, color = Color.Gray)
            Text(text = responseMessage, fontSize = 16.sp, color = Color.Black)
            Text(text = "Catégorie : $category", fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAskQuestionScreen() {
    // Exemple de preview avec des données fictives
    var userInput = "Quels sont les symptômes de la grossesse ?"
    var responseMessage = "Les symptômes incluent fatigue, nausées et seins sensibles."
    var category = "pregnancy"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Poser votre question", fontSize = 24.sp, color = Color.Black)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = userInput,
            onValueChange = {},
            label = { Text("Écrivez votre question ici...") },
            maxLines = 2,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {}, enabled = false) {
            Icon(Icons.Default.Send, contentDescription = "Send", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Envoyer", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Affichage fictif de la réponse
        Text(text = "Réponse de l'IA :", fontSize = 18.sp, color = Color.Gray)
        Text(text = responseMessage, fontSize = 16.sp, color = Color.Black)
        Text(text = "Catégorie : $category", fontSize = 14.sp, color = Color.DarkGray)
    }
}
