package tn.esprit.mamassist.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tn.esprit.mamassist.R

@Composable
fun ArticleDetailScreen(navController: NavController, articleIndex: Int) {
    // List of drawable resources for the article images
    val images = listOf(
        R.drawable.article1,
        R.drawable.article2,
        R.drawable.article3,
        R.drawable.article4,
        R.drawable.article5,
        R.drawable.article6,
        R.drawable.article7
    )

    // Descriptions for each article with some bold sections
    val descriptions = listOf(
        buildAnnotatedString {
            append("Proper nutrition during pregnancy and postpartum is crucial for both the mother’s and baby’s health. As a mother, focusing on a balanced diet will help you maintain energy, support breastfeeding, and speed up recovery after childbirth.\n\n")
            withStyle(style = MaterialTheme.typography.h6.toSpanStyle()) {
                append("Key Points:\n")
            }
            append("- Pregnancy: Ensure you’re consuming enough folic acid, iron, calcium, and omega-3 fatty acids to support fetal development and avoid complications like anemia.\n")
            append("- Postpartum: After giving birth, focus on eating foods rich in fiber, healthy fats, and protein. This will help with healing and replenish your body’s nutrients lost during labor.\n")
            append("- Breastfeeding: Lactating mothers need additional calories, but it’s important to focus on healthy snacks and meals such as whole grains, fruits, vegetables, and lean proteins.\n")
            append("- Hydration: Drink plenty of water to stay hydrated, especially if you are breastfeeding.\n")
        },

        buildAnnotatedString {
            append("After childbirth, the body goes through a period of recovery and adjustment. Postpartum care is essential for both physical and emotional health. It’s important to give your body time to heal while also caring for your mental well-being.\n\n")
            withStyle(style = MaterialTheme.typography.h6.toSpanStyle()) {
                append("Key Points:\n")
            }
            append("- Physical Recovery: Focus on pelvic floor exercises, gentle stretching, and gradually reintroducing physical activity. Pay attention to perineal care, especially if you had stitches or a cesarean section.\n")
            append("- Emotional Recovery: Hormonal changes after birth can lead to mood swings or even postpartum depression. Reach out for help if you feel overwhelmed. Talk to your doctor if you experience signs of depression.\n")
            append("- Rest and Sleep: Make time for rest whenever you can. Sleep deprivation can slow down recovery, so it’s important to rest as much as possible.\n")
            append("- Check-ups: Don’t forget your postpartum check-up. It’s an opportunity for your doctor to ensure that your body is healing properly and to discuss any concerns you may have.\n")
        },

        buildAnnotatedString {
            append("Vaccinations are a vital part of protecting your baby from serious diseases. The first year of your baby’s life includes a series of vaccinations to safeguard their health, and it’s important to stay on schedule with these immunizations.\n\n")
            withStyle(style = MaterialTheme.typography.h6.toSpanStyle()) {
                append("Key Points:\n")
            }
            append("- Recommended Vaccines: Common vaccines during the first year include hepatitis B, DTP (diphtheria, tetanus, and pertussis), Hib (Haemophilus influenzae type b), IPV (polio), and MMR (measles, mumps, rubella).\n")
            append("- Timing: These vaccines are generally given at 2, 4, and 6 months, with additional doses at 12–18 months. Check with your pediatrician to stay on track.\n")
            append("- Benefits: Vaccinations help protect against serious illnesses like measles, whooping cough, and polio. They also contribute to herd immunity, which helps protect vulnerable members of the community.\n")
            append("- Myths vs. Facts: Address common myths about vaccines, such as fears of side effects, and emphasize their importance for the health and safety of the baby.\n")
        },

        buildAnnotatedString {
            append("Establishing a secure emotional bond with your baby is essential for their development and well-being. Early attachment helps babies feel safe, nurtured, and supported, forming the foundation for future emotional and social skills.\n\n")
            withStyle(style = MaterialTheme.typography.h6.toSpanStyle()) {
                append("Key Points:\n")
            }
            append("- Skin-to-Skin Contact: Holding your baby against your skin promotes bonding, helps regulate their body temperature, and fosters a sense of security.\n")
            append("- Responsive Parenting: Respond to your baby’s cries, cooing, and other signals. This helps them feel understood and loved.\n")
            append("- Eye Contact and Talking: Engage with your baby through eye contact, speaking, and singing. Even though your baby can’t respond yet, these interactions help build the foundation for communication.\n")
            append("- Breastfeeding and Bonding: Breastfeeding provides not just nourishment but also emotional bonding time. It’s an opportunity for physical closeness and building a deeper connection.\n")
        },

        buildAnnotatedString {
            append("Many new mothers experience the “baby blues” after childbirth, which include feelings of sadness, anxiety, or irritability. In some cases, these feelings may turn into postpartum depression, a more serious condition that requires professional support.\n\n")
            withStyle(style = MaterialTheme.typography.h6.toSpanStyle()) {
                append("Key Points:\n")
            }
            append("- Baby Blues: These symptoms are common in the first few days to weeks after birth and are typically temporary. They include mood swings, tearfulness, and irritability.\n")
            append("- Postpartum Depression (PPD): PPD is more severe and can last for months. It includes feelings of hopelessness, extreme fatigue, and detachment from the baby.\n")
            append("- Treatment: If you experience symptoms of PPD, seek help from a healthcare professional. Therapy, medication, and support from family and friends can help.\n")
            append("- Self-care: Take breaks, rest when you can, and connect with other mothers for support. Don’t be afraid to ask for help from your partner, friends, or family.\n")
        },

        buildAnnotatedString {
            append("Sleep deprivation is one of the most common challenges new mothers face. Babies often wake up multiple times throughout the night, which can disrupt the mother’s sleep cycle. Managing this lack of sleep is essential for your health and well-being.\n\n")
            withStyle(style = MaterialTheme.typography.h6.toSpanStyle()) {
                append("Key Points:\n")
            }
            append("- Sleep Training: Start by establishing a bedtime routine for your baby. This can help them differentiate between day and night and gradually sleep longer stretches.\n")
            append("- Sleep when the Baby Sleeps: This classic advice is helpful. If possible, try to take naps during the day when your baby is asleep to catch up on rest.\n")
            append("- Share Night Duties: If possible, alternate nighttime duties with your partner or a family member. If you’re breastfeeding, consider pumping to allow your partner to take over a feeding.\n")
            append("- Healthy Sleep Habits: Limit screen time before bed and create a calming environment with dim lights, a white noise machine, or soft music to help both you and your baby sleep better.\n")
        },

        buildAnnotatedString {
            append("Teething can be a difficult time for both babies and parents. As your baby’s teeth start to emerge, they may experience pain and discomfort. Understanding teething symptoms and how to ease them can help make this phase easier.\n\n")
            withStyle(style = MaterialTheme.typography.h6.toSpanStyle()) {
                append("Key Points:\n")
            }
            append("- Symptoms: Teething symptoms include drooling, fussiness, chewing on objects, and sometimes a slight fever. Every baby experiences teething differently.\n")
            append("- Relief Methods: Offer your baby teething toys, a chilled washcloth to chew on, or gentle gum massages to help soothe the discomfort.\n")
            append("- Pain Relief: Over-the-counter teething gels or medications, like acetaminophen, may help, but always consult with your pediatrician before using any medication.\n")
            append("- When to Call the Doctor: If your baby has a high fever, diarrhea, or other unusual symptoms, consult your pediatrician as these could be signs of something other than teething.\n")
        }
    )

    val image = images[articleIndex] // Get image based on articleIndex
    val description = descriptions[articleIndex] // Get description based on articleIndex

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Article Details", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                backgroundColor = Color(0xFFD2A27A),
                elevation = 8.dp
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()), // Make the entire content scrollable
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Display the image at the top of the screen without border radius
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Article Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp), // No border radius
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Title "Description"
                Text(
                    text = "Description",
                    fontSize = 22.sp,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp)) // Spacer between title and description

                // Display the article description text with rich formatting
                Text(
                    text = description,
                    fontSize = 18.sp,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.weight(1f)) // Spacer to push the button to the bottom

                // "Ask Me" button at the bottom
                Button(
                    onClick = { /* Handle "Ask Me" button click */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFA67A5B)), // You can adjust color here
                    shape = RoundedCornerShape(50)
                ) {
                    Text(text = "Ask Me", color = Color.White)
                }
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewArticleDetailScreen() {
    // Créer un NavController fictif pour l'aperçu
    val navController = rememberNavController()

    // Exemple d'aperçu pour le premier article (index 0)
    ArticleDetailScreen(navController = navController, articleIndex = 0)
}
