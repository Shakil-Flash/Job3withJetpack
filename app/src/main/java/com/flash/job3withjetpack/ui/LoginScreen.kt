package com.flash.job3withjetpack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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


@Composable
fun LoginScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(50.dp)
            .background(Color.Black),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {
        Text(
            text = "Shared Location",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .padding(5.dp)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome",
            color = Color.Cyan,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(50.dp))


        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {Text("Mobile Number")},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {Text("Password")},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Login",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { /*TODO*/ },
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


@Preview(showSystemUi = true)
@Composable
fun Preview() {
    LoginScreen()
}