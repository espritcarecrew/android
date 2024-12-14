package tn.esprit.mamassist.Pregnant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.R

@Composable
fun PregnancyToolsScreen(navController: NavController) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFFFF8F5)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Barre supérieure (bouton retour et actions)

            // Zone d'image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.outils), // Remplacez par votre image
                    contentDescription = "Outils de grossesse",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height((800).dp)
                        .fillMaxSize()

                )
            }

            // Texte principal
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Outils de grossesse",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Consultez nos outils pour une grossesse en santé. Les femmes peuvent être enceintes sans même le savoir !",
                    style = TextStyle(fontSize = 16.sp, color = Color.DarkGray),
                    textAlign = TextAlign.Center
                )
            }

            // Indicateurs de pagination
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(12.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == 1) Color(0xFF7C4DFF) else Color.LightGray
                            )
                    )
                }
            }

            // Bouton suivant
            IconButton(
                onClick = { navController.navigate("PregnancyTrackerScreen") },
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 16.dp, bottom = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.next), // Remplacez par votre icône
                    contentDescription = "Suivant",
                    tint = Color(0xFF7C4DFF),
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PregnancyToolsScreenPreview() {
    PregnancyToolsScreen(navController = rememberNavController())
}
