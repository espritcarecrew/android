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
fun PregnancyTrackingScreen(navController: NavController) {
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(Color(0xFFFFF8F5)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {



                // Image principale
                Image(
                    painter = painterResource(id = R.drawable.pregnancy_image), // Remplacez par votre image
                    contentDescription = "Suivi de grossesse",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(500.dp)
                        .fillMaxWidth()
                )

                // Texte principal
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Suivi de grossesse",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Suivi de grossesse pour suivre la grossesse et vous aider également à surveiller votre état de santé général.",
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
                                    if (index == 0) Color(0xFF7C4DFF) else Color.LightGray
                                )
                        )
                    }
                }

                // Bouton suivant
                IconButton(
                    onClick = { navController.navigate("BabyGrowthScreen") },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp, bottom = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.next), // Remplacez par votre icône
                        contentDescription = "Suivant",
                        modifier = Modifier.size(36.dp),
                        tint = Color(0xFF0D0E0E) // Couleur orange pour le flash

                    )
                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PregnancyTrackingScreenPreview() {
    PregnancyTrackingScreen(navController = rememberNavController())
}
