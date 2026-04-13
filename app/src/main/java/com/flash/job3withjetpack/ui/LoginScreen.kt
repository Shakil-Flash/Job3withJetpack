package com.flash.job3withjetpack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults.shape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flash.job3withjetpack.repo.Repository
import com.flash.job3withjetpack.viewmodels.AuthViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.flash.job3withjetpack.ui.AuthViewModelFactory



@Composable
fun LoginScreen(
    // In LoginScreen.kt
    viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(Repository())),
    onLoginSuccess: () -> Unit // Add a callback for navigation
) {
    val content = androidx.compose.ui.platform.LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    //Observe results
    val loginResult by viewModel.loginResult.observeAsState()
    val registerResult by viewModel.registerResult.observeAsState()

    //Handle results (e.g., Navigate or show Toast)
    LaunchedEffect(loginResult) {
        loginResult?.let { (success, message) ->
            if (success) {
                // Navigate to another screen
                onLoginSuccess()
            } else {
                // Show error message
                android.widget.Toast.makeText(content, "Login Failed: $message", android.widget.Toast.LENGTH_LONG).show()

            }
        }
    }

    LaunchedEffect(registerResult) {
        registerResult?.let { (success, message) ->
            if (success) {
                android.widget.Toast.makeText(content, "Registration Successful!", android.widget.Toast.LENGTH_LONG).show()
                onLoginSuccess() // This triggers the navigation to FriendList
            } else {
                android.widget.Toast.makeText(content, "Error: $message", android.widget.Toast.LENGTH_LONG).show()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black
            ),
            shape = RoundedCornerShape(0.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Shared Location",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome",
                color = Color.Cyan,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(40.dp))


            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = {Text("Email")},
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                // HIDEs the password characters
                visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation(),
                keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                    keyboardType = androidx.compose.ui.text.input.KeyboardType.Password
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val trimmedEmail = email.trim()
                    val trimmedPassword = password.trim()
                    if (trimmedEmail.isNotEmpty() && trimmedPassword.isNotEmpty()) {
                        viewModel.login(trimmedEmail, trimmedPassword)
                    } else {
                        android.widget.Toast.makeText(content, "Please fill in all fields", android.widget.Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Login",
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    val trimmedEmail = email.trim()
                    val trimmedPassword = password.trim()
                    if(trimmedEmail.isNotEmpty() && trimmedPassword.isNotEmpty()) {
                        viewModel.register(trimmedEmail, trimmedPassword)
                    } else {
                        android.widget.Toast.makeText(content, "Please fill in all fields", android.widget.Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Register",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(
        onLoginSuccess = {}
    )
}