import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.BottomNavigation.BottomBar
import tn.esprit.mamassist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainInterface(navController: NavHostController) {
    Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        androidx.compose.material3.Text(
                            text = "MamAssist",
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                },
                actions = {
                    androidx.compose.material3.IconButton(onClick = { /* Settings action */ }) {
                        androidx.compose.material3.Icon(
                            Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFD2A27A)
                )
            )
        },
        bottomBar = { BottomBar(navController) },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF6F6F6))
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                InformationSection(navController)
                Spacer(modifier = Modifier.height(16.dp))
                ActionButtons(navController)
                Spacer(modifier = Modifier.height(16.dp))
                Action(navController)
                Spacer(modifier = Modifier.height(16.dp))
                WhoAreYouSection(navController)
                Spacer(modifier = Modifier.height(16.dp))
                TodayQuestionSection()
                Spacer(modifier = Modifier.height(16.dp))
                MoodButton(onClick = { /* Ajouter une action ici */ })
            }
        }
    )
}
@Composable
fun MoodButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFFFF758C), Color(0xFFF25A3D))
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 16.dp, horizontal = 24.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "How are you today?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Log your mood and get support and tips",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
@Composable
fun TodayQuestionSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Today's question",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "How are you feeling today?",
            fontSize = 14.sp,
            color = Color(0xFF666666)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Action for "As usual" */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB3E5FC)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "As usual", color = Color(0xFF0288D1))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Action for "Nauseous" */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB3E5FC)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Nauseous", color = Color(0xFF0288D1))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Action for "Nauseous" */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB3E5FC)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Mole pain", color = Color(0xFF0288D1))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Action for "Nauseous" */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFB3E5FC)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Strange", color = Color(0xFF0288D1))
        }
    }
}
@Composable
fun ActionButtons(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { /* Action for first button */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6200EA)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "What Happens in Months", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { navController.navigate("medicalHelpScreen")  },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF06292)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Posez vos questions à nos praticiens", color = Color.White)
        }
    }
}
@Composable
fun WhoAreYouSection(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hey, who are you?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.navigate("pregnancyTracking") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE3DFFF)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "I'm pregnant", color = Color(0xFF6200EA))
        }

        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Action for "I have children" */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFE3DFFF)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "I have children", color = Color(0xFF6200EA))
        }
    }
}

@Composable
fun InformationSection(navController: NavController) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        val images = listOf(
            R.drawable.article1,
            R.drawable.article2,
            R.drawable.article3
        )
        items(images.size) { index ->
            Box(
                modifier = Modifier
                    .fillParentMaxWidth(0.85f)
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.LightGray)
                    .clickable {
                        navController.navigate("articleDetail/$index")
                    }
            ) {
                Image(
                    painter = painterResource(id = images[index]),
                    contentDescription = "Article ${index + 1}",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    androidx.compose.material3.Text(
                        text = "Article ${index + 1}",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .background(
                                Color(0xAA000000),
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
fun Action(navController: NavController) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F6F6))
            .padding(vertical = 16.dp)
    ) {
        ActionItem(
            imageRes = R.drawable.scale,
            imageSize = 60.dp,
            hasBadge = true,
            onClick = { navController.navigate("WeightTrackerScreen") }
        )
        ActionItem(
            imageRes = R.drawable.heart,
            imageSize = 80.dp,
            hasBadge = true,
            onClick = { navController.navigate("BloodPressureInstructionsScreen") }
        )
        ActionItem(
            imageRes = R.drawable.stethoscope,
            imageSize = 80.dp,
            hasBadge = true,
            onClick = { navController.navigate("SymptomsScreen") }
        )
    }
}

@Composable
fun ActionItem(imageRes: Int, imageSize: Dp, hasBadge: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(Color(0xFFFCE4EC), shape = CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(imageSize)
        )
        if (hasBadge) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Color(0xFFFFC1C1), shape = CircleShape)
                    .align(Alignment.TopEnd),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainInterface() {
    val navController = rememberNavController()
    MainInterface(navController = navController)
}
