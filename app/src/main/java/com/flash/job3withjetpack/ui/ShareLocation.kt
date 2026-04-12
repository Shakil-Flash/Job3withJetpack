package com.flash.job3withjetpack.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShareLocation() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),

        ) {

            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )

            Text(
                text = "Get Location",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(10.dp)

        ) {

            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )

            Text(
                text = "Save Location",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth()
                .height(50.dp
                ),
            shape = RoundedCornerShape(10.dp)

        ) {

            Icon(
                imageVector = Icons.Default.Map,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )

            Text(
                text = "Show Location",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun PreviewShareLocation() {
    ShareLocation()

}