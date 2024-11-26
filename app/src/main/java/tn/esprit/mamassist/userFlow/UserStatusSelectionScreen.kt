package tn.esprit.mamassist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserStatusSelectionScreen(
    onPregnantSelected: () -> Unit,
    onMotherSelected: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to MamAssist!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFFFC1E3), RoundedCornerShape(12.dp))
                .clickable { onPregnantSelected() }
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "I'm Pregnant", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFFB3E5FC), RoundedCornerShape(12.dp))
                .clickable { onMotherSelected() }
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "I'm a Mother", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
