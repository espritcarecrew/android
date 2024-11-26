package tn.esprit.mamassist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

@Composable
fun HealthAppHomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF4D92FF), Color.White)
                )
            )
            .padding(16.dp)
    ) {
        // Header Section
        HeaderSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        SearchBar()

        Spacer(modifier = Modifier.height(16.dp))

        // Categories
        Text(
            text = "Categories",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        CategoriesSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Recommended Doctors
        SectionWithSeeAll("Recommended Doctors")
        RecommendedDoctorsSection()

        Spacer(modifier = Modifier.height(16.dp))

        // Consultation Schedule
        SectionWithSeeAll("Consultation Schedule")
        ConsultationSchedule()
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = "Good Morning and stay healthy",
                color = Color.White,
                fontSize = 14.sp
            )
            Text(
                text = "Find your desired ",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Doctor Right Now! ",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notifications",
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun SearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icône de recherche
            Icon(
                imageVector = Icons.Default.Search, // Icône par défaut pour la recherche
                contentDescription = "Search Icon",
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp)) // Espacement entre l'icône et le texte
            // Texte d'indication
            Text(
                text = "Search Doctor or Symptom",
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
    }
}


@Composable
fun CategoriesSection() {
    // Liste des catégories
    val categories = listOf(
        "Gynécologie", "Pédiatrie", "Endocrinologie",
        "Nutritionniste", "Psychologue", "Kinésithérapeute",
        "Cardiologie", "Pneumologie"
    )

    // Section avec LazyRow pour un défilement horizontal
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(categories) { category ->
            Column(
                modifier = Modifier
                    .clickable { /* Handle click */ },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color(0xFFEBF2FF), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    // Première lettre de la catégorie comme icône
                    Text(
                        text = category.first().toString(),
                        fontSize = 20.sp,
                        color = Color(0xFF4D92FF)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                // Nom de la catégorie
                Text(
                    text = category,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Composable
fun SectionWithSeeAll(title: String) {
    Spacer(modifier = Modifier.height(16.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, fontSize = 18.sp)

        Text(
            text = "See All",
            fontSize = 14.sp,
            color = Color(0xFF9C27B0),
            modifier = Modifier.clickable { /* Handle click */ }
        )
    }
}
@Composable
fun RecommendedDoctorsSection() {
    Spacer(modifier = Modifier.height(16.dp))

    // Liste des docteurs recommandés
    val doctors = listOf(
        "Dr. Amelia Daniel\nDermatologist",
        "Dr. Erik Wagner\nUrology",
        "Dr. Benjamin Scott\nCardiology",
        "Dr. Sarah Johnson\nPediatrics",
        "Dr. Mark Spencer\nEndocrinology"
    )

    // LazyRow pour le défilement horizontal
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(doctors) { doctor ->
            // Variable d'état pour suivre l'état du favori
            var isFavorite by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .width(140.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Conteneur pour l'image et l'icône de favoris
                    Box(
                        modifier = Modifier
                            .size(140.dp)
                            .background(Color.LightGray) // Fond de l'image
                            .clip(RoundedCornerShape(8.dp)), // Forme carrée avec coins arrondis
                        contentAlignment = Alignment.TopEnd
                    ) {
                        // Image du docteur
                        Image(
                            painter = painterResource(id = R.drawable.doctor), // Image locale
                            contentDescription = "Doctor Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Icône de favoris cliquable
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite Icon",
                            tint = if (isFavorite) Color.Red else Color.Gray,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(4.dp)
                                .background(Color.White)
                                .clickable {
                                    isFavorite = !isFavorite // Inverser l'état
                                }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    // Nom et spécialité du docteur
                    Text(
                        text = doctor,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}


@Composable
fun ConsultationSchedule() {
    val doctors = listOf(
        "Dr. Jackson Moraes\nDermatology & Leprosy",
        "Dr. Amelia Daniel\nDermatologist",
        "Dr. Erik Wagner\nUrology",
        "Dr. Benjamin Scott\nCardiology",
        "Dr. Sarah Connor\nEndocrinology",
        "Dr. John Smith\nPediatrics"
    )

    // Ajout d'un LazyColumn pour gérer le défilement vertical
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp) // Espacement entre les éléments
    ) {
        items(doctors) { doctor ->
            // Boîte pour chaque docteur
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(16.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically, // Alignement vertical
                    horizontalArrangement = Arrangement.Start // Alignement horizontal à gauche
                ) {
                    // Image du docteur
                    Image(
                        painter = painterResource(id = R.drawable.doctor), // Image locale
                        contentDescription = "Doctor Image",
                        modifier = Modifier
                            .size(100.dp) // Taille de l'image
                            .padding(end = 16.dp) // Espacement entre l'image et le texte
                    )

                    // Texte du docteur
                    Text(
                        text = doctor,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHealthAppHomeScreen() {
    HealthAppHomeScreen()
}
