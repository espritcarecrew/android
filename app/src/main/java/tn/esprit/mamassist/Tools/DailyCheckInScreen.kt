@file:OptIn(ExperimentalMaterial3Api::class)

package tn.esprit.mamassist.Tools

import SharedPreferencesManager
import android.widget.CalendarView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.data.network.ApiClient
import tn.esprit.mamassist.data.network.CheckInRequest
import tn.esprit.mamassist.ui.theme.MamAssistTheme
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HeaderSection(date: String, weekInfo: String, onClose: () -> Unit, onDateSelected: (String) -> Unit) {
    var isCalendarOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFAC50CC)) // Couleur violette
            .padding(vertical = 16.dp)
    ) {
        // Bouton de fermeture aligné à droite
        IconButton(
            onClick = onClose,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.Black
            )
        }

        // Date et semaine centrées verticalement
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Section pour la date
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(50))
                    .clickable { isCalendarOpen = true } // Ouvre le calendrier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = date,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF624A82) // Couleur violette pour le texte
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    tint = Color(0xFF624A82)
                )
            }

            // Informations sur la semaine
            Text(
                text = weekInfo,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }

    // Afficher la boîte de dialogue pour le calendrier
    if (isCalendarOpen) {
        CalendarDialog(
            onDateSelected = {
                onDateSelected(it)
                isCalendarOpen = false
            },
            onDismiss = { isCalendarOpen = false }
        )
    }
}

@Composable
fun CalendarDialog(onDateSelected: (String) -> Unit, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Afficher un CalendarView Android
                AndroidView(
                    factory = { CalendarView(it) },
                    update = { calendarView ->
                        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
                            val calendar = Calendar.getInstance()
                            calendar.set(year, month, dayOfMonth)

                            val formatter = SimpleDateFormat("dd MMM", Locale.getDefault())
                            val formattedDate = formatter.format(calendar.time)
                            onDateSelected(formattedDate)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Bouton de fermeture
                Button(onClick = onDismiss) {
                    Text("Close")
                }
            }
        }
    }
}

@Composable
fun DailyCheckInScreen(navController: NavController) {

    val context = LocalContext.current
    val sharedPreferencesManager = SharedPreferencesManager(context)
    val userId = sharedPreferencesManager.getUserId()
    if (userId == null) {
        println("Error: User ID is missing. Please login again.")
        return
    }
    val selectedDate = remember { mutableStateOf("26 Nov") }
    val selectedMood = remember { mutableStateOf(2) } // Default mood (neutral)
    // Map des emojis avec descriptions
    val moodMap = listOf(
        "😡" to "Angry 😡",
        "🙁" to "Sad 😟",
        "😐" to "Neutral 😐",
        "🙂" to "Content 🙂",
        "😊" to "Happy 😊"
    )

    val discomforts = listOf(
        "Contractions", "Heartburn", "Swelling", "Anxiety",
        "Back pain", "Pain", "Pelvic pain", "Fatigue",
        "Depression", "Nausea", "Fluctuating emotions"
    )
    val selectedDiscomforts = remember { mutableStateListOf<String>() }
    val elaborationText = remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Header Section
        HeaderSection(
            date = selectedDate.value,
            weekInfo = "4w+3d",
            onClose = { /* Handle close action */ },
            onDateSelected = { newDate -> selectedDate.value = newDate }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Texte "How are you today?"
        Text(
            text = "How are you today?",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Rangée des humeurs
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            moodMap.forEachIndexed { index, (emoji, description) ->
                Text(
                    text = emoji,
                    fontSize = 32.sp,
                    modifier = Modifier
                        .clickable { selectedMood.value = index }
                        .background(
                            if (selectedMood.value == index) Color.Gray.copy(alpha = 0.2f) else Color.Transparent,
                            shape = RoundedCornerShape(50)
                        )
                        .padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Section de sélection des inconforts
        Text(
            text = "Are you experiencing any discomfort?",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            discomforts.chunked(3).forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    row.forEach { discomfort ->
                        val isSelected = selectedDiscomforts.contains(discomfort)
                        Text(
                            text = discomfort,
                            modifier = Modifier
                                .clickable {
                                    if (isSelected) selectedDiscomforts.remove(discomfort)
                                    else selectedDiscomforts.add(discomfort)
                                }
                                .background(
                                    if (isSelected) Color.Blue.copy(alpha = 0.2f) else Color.Gray.copy(alpha = 0.1f),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 8.dp),
                            color = if (isSelected) Color.Blue else Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))


        // Champ de texte pour les notes supplémentaires
        Text(
            text = "Do you want to elaborate?",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        TextField(
            value = elaborationText.value,
            onValueChange = { elaborationText.value = it },
            placeholder = { Text("Write your notes here...") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Gray.copy(alpha = 0.1f)
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bouton Save
        Button(
            onClick = {
                val moodDescription = moodMap[selectedMood.value].second
                val checkInRequest = CheckInRequest(
                    userId = userId,
                    date = selectedDate.value,
                    mood = moodDescription,
                    discomforts = selectedDiscomforts,
                    elaboration = elaborationText.value.text
                )

                // Appel API
                val call = ApiClient.apiService.createCheckIn(checkInRequest)
                call.enqueue(object : retrofit2.Callback<Void> {
                    override fun onResponse(call: retrofit2.Call<Void>, response: retrofit2.Response<Void>) {
                        if (response.isSuccessful) {
                            println("Check-In enregistré avec succès !")
                        } else {
                            println("Erreur API : ${response.errorBody()?.string()}")
                        }
                    }

                    override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                        println("Erreur réseau : ${t.message}")
                    }
                })
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Save")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DailyCheckInScreenPreview() {
    MamAssistTheme {
        DailyCheckInScreen(navController = rememberNavController())
    }
}
