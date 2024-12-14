package tn.esprit.mamassist.Pregnant
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun BabyGrowthScreen(navController: NavController) {
      Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("", style = MaterialTheme.typography.h6) },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
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
                    .background(Color(0xFFFFF8F5)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Barre supérieure (bouton retour et actions)

                // Image principale
                Image(
                    painter = painterResource(id = R.drawable.baby_growth_image), // Remplacez par votre image
                    contentDescription = "Croissance du bébé",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(600.dp)
                        .fillMaxWidth()
                )

                // Texte principal
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Croissance du bébé",
                        style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Connaître la croissance de votre bébé est sûr et facile dans le ventre de la mère.",
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
                    onClick = { navController.navigate("PregnancyToolsScreen") },
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
    )
}



@Preview(showBackground = true)
@Composable
fun BabyGrowthScreenPreview() {
    BabyGrowthScreen(navController = rememberNavController())
}



