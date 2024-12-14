package tn.esprit.mamassist.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
fun WeightTrackerScreen(navController: NavController, onAgreeClick: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFF4CAF50),
                title = { Text(text = "You're 1 week pregnant", color = Color.White) },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { navController.navigate("maininterface") }) {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Image principale
                Image(
                    painter = painterResource(id = R.drawable.iscale), // Remplacez par votre image
                    contentDescription = "Weight scale",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .padding(top = 16.dp)
                )

                // Texte principal
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "No data.",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Add your weight and height.",
                        style = TextStyle(fontSize = 16.sp, color = Color.Gray),
                        textAlign = TextAlign.Center
                    )
                }

                // Bouton principal
                Button(
                    onClick = { /* Action pour ajouter des données */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF5722))
                ) {
                    Text(text = "Add", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun WeightTrackerScreenPreview() {
    WeightTrackerScreen(navController = rememberNavController())
}
