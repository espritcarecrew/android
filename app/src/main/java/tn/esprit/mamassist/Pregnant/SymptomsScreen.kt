package tn.esprit.mamassist.Pregnant

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SymptomsScreen(navController: NavController) {
    var selectedSymptoms by remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Symptômes", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("maininterface") }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }


                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF6F6F6))
                    .verticalScroll(rememberScrollState()) // Ajout du défilement vertical
            ) {
                // Section de la date et des onglets
                SymptomsHeader()

                Spacer(modifier = Modifier.height(16.dp))

                // Section générale
                SymptomsSection(
                    title = "GÉNÉRAL",
                    symptoms = listOf(
                        "Tout va bien" to R.drawable.good,
                        "Beaucoup d'activité" to R.drawable.activity,
                        "Épuisée" to R.drawable.tired,
                        "Œdème pieds/jambes" to R.drawable.swelling,
                        "Démangeaisons de la peau" to R.drawable.dema

                    ),
                    selectedSymptoms = selectedSymptoms,
                    onSelect = { selected ->
                        selectedSymptoms = toggleSelection(selectedSymptoms, selected)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Section zone du bassin
                SymptomsSection(
                    title = "ZONE DU BASSIN",
                    symptoms = listOf(
                        "Douleur dans le bassin" to R.drawable.pelvic_pain,
                        "Contractions utérines" to R.drawable.contractions,
                        "Douleur en faisant pipi" to R.drawable.urine_pain,
                        "Démangeaisons vulvaires" to R.drawable.itching,
                        "Perte de sang" to R.drawable.sang,
                        "Perte de liquide amniotique" to R.drawable.liquide,
                        "Abscence mouvements bébé>12h" to R.drawable.mvbebe


                    ),
                    selectedSymptoms = selectedSymptoms,
                    onSelect = { selected ->
                        selectedSymptoms = toggleSelection(selectedSymptoms, selected)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Section tête
                SymptomsSection(
                    title = "TÊTE",
                    symptoms = listOf(
                        "Vertiges" to R.drawable.dizziness,
                        "Évanouissement" to R.drawable.fainting,
                        "Symptômes rhume/grippe" to R.drawable.flu,
                        "Maux de tête" to R.drawable.headache,
                        "Petites mouches devant les yeux" to R.drawable.headache,
                        "Acouphènes" to R.drawable.ac

                    ),
                    selectedSymptoms = selectedSymptoms,
                    onSelect = { selected ->
                        selectedSymptoms = toggleSelection(selectedSymptoms, selected)
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Section ventre ou estomac
                SymptomsSection(
                    title = "VENTRE OU ESTOMAC",
                    symptoms = listOf(
                        "Nausée du matin" to R.drawable.nause,
                        "Forte nausée toute la journée" to R.drawable.forte,
                        "Reflux gastro-oesophagien" to R.drawable.reflux,
                        "Douleur dans l'estomac" to R.drawable.douleur,
                        "Désordres digestifs" to R.drawable.desordre
                    ),
                    selectedSymptoms = selectedSymptoms,
                    onSelect = { selected ->
                        selectedSymptoms = toggleSelection(selectedSymptoms, selected)
                    }
                )

                Spacer(modifier = Modifier.height(32.dp))

                // Bouton enregistrer
                Button(
                    onClick = { /* Enregistrer les symptômes */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFC1C1))
                ) {
                    Text(text = "Enregistrer les symptômes", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    )
}

@Composable
fun SymptomsHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "7 décembre",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Symptômes",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Historique",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun SymptomsSection(
    title: String,
    symptoms: List<Pair<String, Int>>,
    selectedSymptoms: Set<String>,
    onSelect: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()) // Ajouter le défilement horizontal
        ) {
            symptoms.forEach { (symptom, icon) ->
                SymptomItem(
                    label = symptom,
                    iconRes = icon,
                    isSelected = selectedSymptoms.contains(symptom),
                    onClick = { onSelect(symptom) }
                )
            }
        }
    }
}

@Composable
fun SymptomItem(label: String, iconRes: Int, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .size(100.dp)
            .clickable(onClick = onClick)
            .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) Color(0xFF6200EE) else Color.Gray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(8.dp)) // Espace entre l'image et le texte

        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

fun toggleSelection(currentSelection: Set<String>, item: String): Set<String> {
    return if (currentSelection.contains(item)) {
        currentSelection - item
    } else {
        currentSelection + item
    }
}

@Preview( showBackground = true)
@Composable
fun PreviewSymptomsScreen() {
    SymptomsScreen(navController = rememberNavController())
}
