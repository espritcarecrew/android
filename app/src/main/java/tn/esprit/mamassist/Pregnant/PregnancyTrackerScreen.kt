package tn.esprit.mamassist.Pregnant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
fun PregnancyTrackerScreen(navController: NavController) {
    Scaffold { padding ->
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
                painter = painterResource(id = R.drawable.baby_mother_image), // Remplacez par votre image
                contentDescription = "Baby and Mother",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            // Titre principal
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Pregnancy tracker!",
                    style = TextStyle(
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                Text(
                    text = "Set one of these dates",
                    style = TextStyle(fontSize = 16.sp, color = Color.Gray),
                    textAlign = TextAlign.Center
                )
            }

            // Formulaire avec les champs de saisie
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Action pour la date due */ },
                    label = { Text("Due date") },
                    placeholder = { Text("Your Due Date") },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.calendar), // Remplacez par une icône de calendrier
                            contentDescription = "Calendar Icon"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )

                Text(
                    text = "OR",
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Gray),
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Action pour le dernier cycle */ },
                    label = { Text("Last period") },
                    placeholder = { Text("First day of last period") },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.calendar), // Remplacez par une icône de calendrier
                            contentDescription = "Calendar Icon"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }

            // Bouton principal
            Button(
                onClick = { navController.navigate("pregnantForm") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF4CAF50)),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "Done",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PregnancyTrackerScreenPreview() {
    PregnancyTrackerScreen(navController = rememberNavController())
}
