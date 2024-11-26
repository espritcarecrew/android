package tn.esprit.mamassist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tn.esprit.mamassist.ui.theme.Pink40

@Composable
fun YourSituationScreen(
    onPregnantClick: () -> Unit = {}, // Par défaut, actions vides pour le preview
    onAlreadyHaveBabiesClick: () -> Unit = {}
) {
    // Image de fond
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Utilisation de l'image avec ContentScale.Cover
        Image(
            painter = painterResource(id = R.drawable.situation), // Remplacez par l'ID de votre image
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Utilisez ContentScale.Crop pour un redimensionnement
        )

        // Contenu par-dessus l'image
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Texte de titre
            Text(
                text = "Your Situation",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold), // Texte en gras
                color = Color.Black, // Contraste avec l'image
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Bouton "Pregnant"
            Button(
                onClick = onPregnantClick,
                colors = ButtonDefaults.buttonColors(containerColor = Pink40), // Spécifiez la couleur de fond ici
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(vertical = 120.dp)
                    .fillMaxWidth(0.5f) // Largeur ajustée à 80% de la largeur de l'écran
                    .height(70.dp) // Hauteur du bouton

            ) {
                Text(text = "Pregnant",color = Color.Black)
            }

            // Bouton "Already Have Babies"
            Button(
                onClick = onAlreadyHaveBabiesClick,
                colors = ButtonDefaults.buttonColors(containerColor = Pink40), // Spécifiez la couleur de fond ici
                modifier = Modifier
                    .align(Alignment.Start) // Alignement à gauche
                    .padding(vertical = 120.dp) // Décalage vertical
                    .fillMaxWidth(0.5f) // Largeur ajustée à 50% de la largeur de l'écran
                    .height(70.dp) // Hauteur du bouton
            ) {
                Text(text = "Already Have Babies", color = Color.Black)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun YourSituationScreenPreview() {
    YourSituationScreen(
        onPregnantClick = { println("Pregnant button clicked!") },
        onAlreadyHaveBabiesClick = { println("Already Have Babies button clicked!") }
    )
}
