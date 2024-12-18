package tn.esprit.mamassist.Authentification.inscrire

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import tn.esprit.mamassist.R
import tn.esprit.mamassist.ui.theme.MamAssistTheme
import tn.esprit.mamassist.ui.theme.VSky
@Composable
fun RegisterScreen(viewModel: RegisterViewModel,onNavigateToLogin: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("usernormal") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val signUpState by viewModel.signUpUiState.observeAsState()
    LaunchedEffect(signUpState?.isSignedUp) {
        if (signUpState?.isSignedUp == true) {
            Log.d("RegisterScreen", "Navigating to InscrireScreenWithPager")
            onNavigateToLogin()
        }
    }

    signUpState?.errorMessage?.let { error ->
        Text(text = error, color = Color.Red)
    }

    if (signUpState?.isLoading == true) {
        CircularProgressIndicator()
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> imageUri = uri }
    )
    val roles = listOf("usernormal", "medecin")
    // Appliquer le thème clair spécifiquement pour cet écran
    MamAssistTheme() { // Ici, on force le thème clair
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.register),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier.fillMaxSize())

            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.size(200.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    if (imageUri == null) {
                        Image(
                            modifier = Modifier.size(200.dp).clip(CircleShape),
                            painter = painterResource(id = R.drawable.userr),
                            contentDescription = "No user image",
                        )
                    } else {
                        AsyncImage(
                            model = imageUri,
                            modifier = Modifier.size(200.dp).clip(CircleShape),
                            contentDescription = "User Image"
                        )
                    }
                    IconButton(
                        onClick = {
                            launcher.launch("image/*")
                        },
                        modifier = Modifier.clip(CircleShape).background(Color.Gray.copy(alpha = 0.7f))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.cam),
                            contentDescription = "Change Profile Picture",
                            tint = Color.White
                        )
                    }
                }

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Full Name") },
                    modifier = Modifier.fillMaxWidth().padding(20.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Email") },
                    modifier = Modifier.fillMaxWidth().padding(20.dp)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth().padding(20.dp)
                )

                OutlinedTextField(
                    value = bio,
                    onValueChange = { bio = it },
                    label = { Text(text = "Short Bio") },
                    modifier = Modifier.fillMaxWidth().padding(20.dp)
                )
                Box {
                    Text("Role:")
                    var expanded by remember { mutableStateOf(false) }
                    Text(role, Modifier.clickable { expanded = true })
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        roles.forEach {
                            DropdownMenuItem(
                                text = { Text(it) },
                                onClick = { role = it; expanded = false }
                            )
                        }
                    }}
                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        Log.d("RegisterScreen", "Register button clicked")
                        viewModel.signUpUser(name, email, password, bio, imageUri.toString(), role)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = VSky)
                ) {
                    Text(text = "Register", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
                ) {
                    Text(text = "Already have an account?", color = Color.Black)
                    Text(
                        text = "Login here",
                        color = Color.Black,
                        style = TextStyle(fontWeight = FontWeight.Bold),
                        modifier = Modifier.clickable { onNavigateToLogin() }
                    )
                }
            }
        }
    }
}


