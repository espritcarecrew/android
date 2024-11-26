package tn.esprit.mamassist.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.OutlinedTextField
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
                        //contentAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "MamAssist",
                            color = Color.White, // Set text color to white
                            fontSize = 20.sp // Adjust font size if needed
                        )
                    }
                },
                actions = {
                    // Settings Icon
                    IconButton(onClick = { /* Settings action */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White) // Set icon tint to white
                    }
                },
                backgroundColor = Color(0xFFF48FB1), // Set background color to match ProfileScreen
                elevation = 8.dp
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp) // Add margin around the bottom bar
                    .fillMaxWidth()
                    .shadow(elevation = 10.dp, shape = RoundedCornerShape(24.dp), clip = false) // Add shadow with rounded shape
                    .clip(RoundedCornerShape(24.dp)) // Clip the background to rounded corners
                    .background(Color.White) // Set the background color of the bottom bar
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Add padding for better spacing
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Home Icon
                    IconButton(onClick = { navController.navigate("home") }) {
                        Icon(Icons.Default.Home, contentDescription = "Home", tint = Color(0xFFF48FB1))
                    }
                    // Calendar Icon
                    IconButton(onClick = { /* Placeholder for Calendar */ }) {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Calendar", tint = Color(0xFFF48FB1))
                    }
                    // Cart Icon
                    IconButton(onClick = { /* Navigate to AI Chat screen */ }) {
                        Icon(Icons.Default.Chat, contentDescription = "AI Chat", tint = Color(0xFFF48FB1))
                    }

                    // Profile Icon
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

                // Add the styled search bar
                StyledSearchBar()

                Spacer(modifier = Modifier.height(16.dp))

                // Add more UI elements as needed
                FeaturedSection()

                Spacer(modifier = Modifier.height(16.dp))
                RecommendedCards()


            }
        }
    )
}

@Composable
fun StyledSearchBar() {
    // Remember the state of the text field
    var searchText = remember { mutableStateOf(TextFieldValue("")) }

    // OutlinedTextField with explicit state handling
    OutlinedTextField(
        value = searchText.value, // Provide the state value here
        onValueChange = { newValue ->
            searchText.value = newValue // Update the state when the value changes
        },
        modifier = Modifier
            .fillMaxWidth() // Full width for the search bar
            .padding(vertical = 8.dp) // Padding around the search bar
            .height(50.dp) // Set height of the search bar
            .clip(RoundedCornerShape(30.dp)) // Rounded corners for the search bar
            .background(Color.White) // White background for the search field
            .shadow(1.dp, RoundedCornerShape(1.dp)), // Outer shadow for visibility
        leadingIcon = {
            // Icon inside the search bar
            Icon(Icons.Filled.Search, contentDescription = "Search", tint = Color(0xFFE91E63))
        },
        placeholder = { Text("Search", color = Color.Gray) }, // Placeholder text
        textStyle = MaterialTheme.typography.body1.copy(color = Color.Black), // Text style inside the search bar
        singleLine = true, // One line for the search field
        shape = RoundedCornerShape(30.dp) // Rounded corners
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
fun RecommendedCards() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RecommendedCard(title = "Care", sessions = "Doctors", backgroundColor = Color(0xFFD0CECE))
        RecommendedCard(title = "Milestones", sessions = "Baby Progress", backgroundColor = Color(0xFFD0CECE))
        RecommendedCard(title = "Health", sessions = "Doctor Visits", backgroundColor = Color(0xFFD0CECE))
    }
}

@Composable
fun RecommendedCard(title: String, sessions: String, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .width(120.dp) // Increased width
            .height(140.dp) // Increased height
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = title, fontSize = 18.sp, color = Color.White) // Updated text color
            Text(text = sessions, fontSize = 14.sp, color = Color.White) // Updated text color
        }
    }
}
