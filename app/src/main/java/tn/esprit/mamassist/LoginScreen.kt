package tn.esprit.mamassist

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import tn.esprit.mamassist.ui.theme.VSky

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val emailError = remember { mutableStateOf<String?>(null) }
    val passwordError = remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize().background(Color(0xFFe1d6d9))) // Background color

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Image(
                painter = painterResource(id = R.drawable.logo_mamassist), // Replace with your drawable
                contentDescription = "Login Header Image",
                modifier = Modifier.size(250.dp).padding(bottom = 24.dp),
                contentScale = ContentScale.Crop
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                placeholder = { Text(text = "Enter your email") },
                isError = emailError.value != null,
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            )
            emailError.value?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 30.dp, bottom = 8.dp)
                )
            }

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "Enter your password") },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                isError = passwordError.value != null,
                modifier = Modifier.fillMaxWidth().padding(20.dp)
            )
            passwordError.value?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 30.dp, bottom = 8.dp)
                )
            }

            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        viewModel.loginUser(email, password)
                        onLoginSuccess() // Navigate to home after login
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = VSky),
            ) {
                Text(text = "Login", color = Color.White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Or, login with...",
                fontSize = 15.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally).alpha(0.5f)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                AuthOption(image = R.drawable.google, onClick = { viewModel.googleLogin(context) })
                AuthOption(image = R.drawable.facebook, onClick = { /* Handle Facebook login */ })
                AuthOption(image = R.drawable.apple, onClick = { /* Handle Apple login */ })
            }

            Spacer(modifier = Modifier.height(28.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
            ) {
                Text(text = "Don't have an account?", color = Color.Black)
                Text(
                    text = "Sign up",
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.clickable { onNavigateToRegister() }
                )
            }
        }
    }
}

@Composable
fun AuthOption(image: Int, onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Auth option",
            modifier = Modifier.size(48.dp)
        )
    }
}
