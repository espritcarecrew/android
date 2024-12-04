package tn.esprit.mamassist.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import tn.esprit.mamassist.R

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
                backgroundColor = Color(0xFFD2A27A),
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
                        Icon(Icons.Default.Home, contentDescription = "Home", tint = Color(0xFFD5B895))
                    }
                    IconButton(onClick = { navController.navigate("calendar") }) {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Calendar", tint = Color(0xFFD5B895))
                    }
                    IconButton(onClick = { /* Navigate to AI Chat screen */ }) {
                        Icon(Icons.Default.Chat, contentDescription = "AI Chat", tint = Color(0xFFD5B895))
                    }
                    IconButton(onClick = { navController.navigate("profile") }) {
                        Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color(0xFFD5B895))
                    }
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Welcome Text
                Text(
                    text = "Welcome back!",
                    fontSize = 24.sp,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(start = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Title for the Article Cards
                Text(
                    text = "Articles",
                    fontSize = 20.sp,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(start = 8.dp)
                )

                HorizontalArticleCards(navController = navController)


                Spacer(modifier = Modifier.height(50.dp)) // Added more space before FeaturedSection
                FeaturedSection()

                Spacer(modifier = Modifier.height(16.dp))
                StatisticsCard()
            }
        }
    )
}


@Composable
fun HorizontalArticleCards(navController: NavController) {
    // List of drawable resources for the article images
    val images = listOf(
        R.drawable.article1, // Replace these with your actual drawable names
        R.drawable.article2,
        R.drawable.article3,
        R.drawable.article4,
        R.drawable.article5,
        R.drawable.article6,
        R.drawable.article7
    )

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(images.size) { index ->
            Box(
                modifier = Modifier
                    .fillParentMaxWidth(0.85f) // Make each card ~85% of the screen width
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp)) // Rounded corners
                    .background(Color.LightGray) // Placeholder color for when the image loads
                    .clickable {
                        // Navigate to the article detail screen with the index
                        navController.navigate("articleDetail/$index")
                    }
            ) {
                // Image as the background
                Image(
                    painter = painterResource(id = images[index]),
                    contentDescription = "Article ${index + 1} Background",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)), // Clip image to match card shape
                    contentScale = ContentScale.Crop // Crop the image to fill the box
                )

                // Overlay content (Text, etc.)
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomStart // Align content to the bottom-left
                ) {
                    Text(
                        text = "Article ${index + 1}",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .background(
                                Color(0xAA000000), // Semi-transparent black background for better readability
                                RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun FeaturedSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(Color(0xFFdcbfa6), RoundedCornerShape(16.dp))
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
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFA67A5B)),
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
