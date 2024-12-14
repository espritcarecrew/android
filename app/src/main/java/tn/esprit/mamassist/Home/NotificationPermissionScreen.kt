package tn.esprit.mamassist.Home
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
fun NotificationPermissionScreen(navController: NavController) {
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Header (Logo et Ignorer)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "MamAssist",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    )
                    TextButton(onClick = { navController.navigate("MainInterface") },
                    ) {
                        Text(
                            text = "Ignorer",
                            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
                        )
                    }
                }

                // Image principale
                Image(
                    painter = painterResource(id = R.drawable.notification_image), // Remplacez par votre image
                    contentDescription = "Notification Illustration",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth()
                )

                // Texte principal
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Ne ratez aucun message des praticiens",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Autorisez les notifications afin d'être prévenue lorsqu’un praticien vous répond",
                        style = TextStyle(fontSize = 16.sp, color = Color.Gray),
                        textAlign = TextAlign.Center
                    )
                }

                // Bouton principal
                Button(
                    onClick = { navController.navigate("MainInterface") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Brush.horizontalGradient(
                            colors = listOf(Color(0xFF7B61FF), Color(0xFFEA9EFF))
                        ).toColor()
                    )
                ) {
                    Text(
                        text = "Autoriser les notifications",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    )
}

fun Brush.toColor(): Color {
    return Color(0xFF000000) // Remplacez cette fonction par un utilitaire si besoin
}

@Preview(showBackground = true)
@Composable
fun NotificationPermissionScreenPreview() {
    NotificationPermissionScreen(navController = rememberNavController())
}

