package tn.esprit.mamassist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "MamAssist",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Settings action */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
                    }
                },
                backgroundColor = Color(0xFFF48FB1),
                elevation = 8.dp
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(24.dp), clip = false)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Default.Home, contentDescription = "Home", tint = Color(0xFFF48FB1))
                    }
                    IconButton(onClick = { navController.navigate("calendar") }) {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Calendar", tint = Color(0xFFF48FB1))
                    }
                    IconButton(onClick = { /* Navigate to AI Chat screen */ }) {
                        Icon(Icons.Default.Chat, contentDescription = "AI Chat", tint = Color(0xFFF48FB1))
                    }
                    IconButton(onClick = { navController.navigate("profile") }) {
                        Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color(0xFFF48FB1))
                    }
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(24.dp))
                StyledSearchBar()
                Spacer(modifier = Modifier.height(16.dp))
                FeaturedSection()
                Spacer(modifier = Modifier.height(16.dp))
                StatisticsCard()
            }
        }
    )
}


@Composable
fun StyledSearchBar() {
    var searchText = remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = searchText.value,
        onValueChange = { newValue ->
            searchText.value = newValue
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.White)
            .shadow(1.dp, RoundedCornerShape(1.dp)),
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color(0xE1E91EB6))
        },
        placeholder = { Text("Search", color = Color.Gray) },
        textStyle = MaterialTheme.typography.body1.copy(color = Color.Black),
        singleLine = true,
        shape = RoundedCornerShape(30.dp)
    )
}

@Composable
fun FeaturedSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color(0xFFFFC1CC), RoundedCornerShape(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Baby Details",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* Action for Baby Details */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF48FB1)),
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "See more", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun StatisticsCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color(0xFFD0CECE), RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Statistics",
                fontSize = 24.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Monitor baby growth, health trends, and activity progress.",
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}
