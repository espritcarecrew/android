package tn.esprit.mamassist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@Composable
fun MotherListScreen(navController: NavController) {
    // List of milestones with images
    val items = listOf(
        Pair(R.drawable.mmme, "Milestone 1: Smiling wiuerh iwueryheiuw rwiuerg iuwegrui wgruiwegruigweui rgweiug riuewg riuwegr iuwegrweprg wprg weprugewruwgeurgweuruerwurewgyurg"),
        Pair(R.drawable.motherlistjpg, "Milestone 2: Crawling miling wiuerh iwueryheiuw rwiuerg iuwegrui wgruiwegruigweui rgweiug riuewg riuwegr iuwegrweprg wprg weprugewruwgeurgweuruerwurewgyurg"),
        Pair(R.drawable.mmme, "Milestone 3: Walking miling wiuerh iwueryheiuw rwiuerg iuwegrui wgruiwegruigweui rgweiug riuewg riuwegr iuwegrweprg wprg weprugewruwgeurgweuruerwurewgyurg"),
        Pair(R.drawable.motherlistjpg, "Milestone 3: Walking miling wiuerh iwueryheiuw rwiuerg iuwegrui wgruiwegruigweui rgweiug riuewg riuwegr iuwegrweprg wprg weprugewruwgeurgweuruerwurewgyurg")

    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Add a TopAppBar with a back button
        TopAppBar(
            title = { Text(text = "Mother Milestones") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) { // Go back to the previous screen
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            backgroundColor = Color(0xFFF5F5F5), // Light gray background for the app bar
            contentColor = Color.Black // Black color for the text and icon
        )

        // Scrollable list of cards
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Make the column scrollable
        ) {
            // Iterate over the items and display each one in a card
            for (item in items) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp), // Space between cards
                    elevation = 8.dp, // Add shadow for better visuals
                    backgroundColor = Color(0xFFF5F5F5) // Light gray background for the card
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth() // Ensure full width for the card content
                    ) {
                        // Image at the top of the card
                        Image(
                            painter = painterResource(id = item.first),
                            contentDescription = item.second,
                            contentScale = ContentScale.Crop, // Crop the image to fill the width
                            modifier = Modifier
                                .fillMaxWidth() // Image spans the entire width of the card
                                .height(200.dp) // Set a fixed height for the image
                        )

                        // Text under the image
                        Text(
                            text = item.second,
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                            modifier = Modifier
                                .padding(16.dp) // Padding around the text
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center // Correct alignment for text
                        )
                    }
                }
            }
        }
    }
}
