package tn.esprit.mamassist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.clip

@Composable
fun GynecologicalHistoryScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Antécédents gynécologiques") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back navigation */ }) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_menu_revert), contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle close */ }) {
                        Icon(painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel), contentDescription = "Close")
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
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Souffrez-vous / avez-vous souffert d'une ou plusieurs de ces maladies gynécologiques ?",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF6961)),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                SearchBar(
                    hint = "Rechercher"
                )

                Spacer(modifier = Modifier.height(16.dp))

                val options = listOf(
                    "Syndrome des ovaires polykystiques",
                    "Aucun antécédent gynécologique",
                    "Malformation vaginale",
                    "Conisation",
                    "Fibrome",
                    "Cancer du col"
                )
                var selectedOption by remember { mutableStateOf("") }

                options.forEach { option ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { selectedOption = option }
                            .background(if (selectedOption == option) Color(0xFF7C4DFF) else Color(0xFFF5F5F5))
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = option,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                color = if (selectedOption == option) Color.White else Color.Black
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { /* Handle next action */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF6961))
                ) {
                    Text(text = "Suivant", color = Color.White)
                }
            }
        }
    )
}

@Composable
fun SearchBar(hint: String) {
    var text by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        if (text.isEmpty()) {
            Text(text = hint, color = Color.Gray)
        }
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GynecologicalHistoryScreenPreview() {
    GynecologicalHistoryScreen()
}
