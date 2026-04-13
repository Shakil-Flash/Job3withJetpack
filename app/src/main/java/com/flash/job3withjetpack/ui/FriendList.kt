package com.flash.job3withjetpack.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flash.job3withjetpack.R
import com.flash.job3withjetpack.data.User
import androidx.compose.foundation.lazy.items


import androidx.compose.material3.FloatingActionButton
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold


import androidx.compose.foundation.clickable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.flash.job3withjetpack.repo.Repository
import com.flash.job3withjetpack.viewmodels.UserViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState


import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.FabPosition


@Composable
fun FriendScreen(
    onGetLocationClick: () -> Unit,
    onFriendClick: (Double, Double, String) -> Unit,
    onLogout: () -> Unit,
    viewModel: UserViewModel = viewModel(factory = UserViewModelFactory(Repository()))
) {
    val currentUser by viewModel.currentUser.observeAsState()
    val friends by viewModel.users.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }

    Scaffold(
        floatingActionButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FloatingActionButton(
                    onClick = onLogout,
                    containerColor = Color.Red,
                    contentColor = Color.White
                ) {
                    Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = "Logout")
                }

                FloatingActionButton(onClick = onGetLocationClick) {
                    Icon(Icons.Default.AddLocation, contentDescription = "Get Location")
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            // 🔹 My Profile (TOP FIXED)
            currentUser?.let { user ->
                MyProfileCard(user = user, onClick = {
                    if (user.lat != null && user.lng != null) {
                        onFriendClick(user.lat, user.lng, user.userName)
                    }
                })
            }

            Spacer(modifier = Modifier.height(8.dp))

            // 🔹 Friends List (BOTTOM SCROLLABLE)
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(friends) { user ->
                    FriendItem(user, onClick = {
                        if (user.lat != null && user.lng != null) {
                            onFriendClick(user.lat, user.lng, user.userName)
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun FriendItem(user: User, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable { onClick() }
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(text = user.userName, fontSize = 18.sp)
            Text(text = user.email, fontSize = 14.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Lat: ${user.lat}")
                Text("Lng: ${user.lng}")
            }
        }
    }
}

@Composable
fun MyProfileCard(user: User, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(170.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(R.drawable.img),
                contentDescription = "My Profile",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = user.userName, fontSize = 18.sp)
            Text(text = user.email, fontSize = 14.sp, color = Color.Gray)

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("Lat: ${user.lat}")
                Text("Lng: ${user.lng}")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewFriendList() {
    FriendScreen(onGetLocationClick = {}, onFriendClick = { _, _, _ -> }, onLogout = {})
}

