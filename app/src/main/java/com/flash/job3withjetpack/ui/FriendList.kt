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


@Composable
fun FriendScreen() {

    val myUser = User(
        name = "Flash",
        email = "flash@email.com",
        lat = "10",
        lng = "01"
    )

    val friends = listOf(
        User("Batman", "batman@email.com", "40", "74"),
        User("Superman", "super@email.com", "34", "118"),
        User("Ironman", "iron@email.com", "37", "122"),
        User("Batman", "batman@email.com", "40", "74"),
        User("Superman", "super@email.com", "34", "118"),
        User("Ironman", "iron@email.com", "37", "122"),
        User("Batman", "batman@email.com", "40", "74"),
        User("Superman", "super@email.com", "34", "118"),
        User("Ironman", "iron@email.com", "37", "122"),
        User("Batman", "batman@email.com", "40", "74"),
        User("Superman", "super@email.com", "34", "118"),
        User("Ironman", "iron@email.com", "37", "122"),
        User("Batman", "batman@email.com", "40", "74"),
        User("Superman", "super@email.com", "34", "118"),
        User("Ironman", "iron@email.com", "37", "122"),
        User("Batman", "batman@email.com", "40", "74"),
        User("Superman", "super@email.com", "34", "118"),
        User("Ironman", "iron@email.com", "37", "122")
    )

    Column(modifier = Modifier.fillMaxSize()) {

        // 🔹 My Profile (TOP FIXED)
        MyProfileCard(user = myUser)

        Spacer(modifier = Modifier.height(8.dp))

        // 🔹 Friends List (BOTTOM SCROLLABLE)
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(friends) { user ->
                FriendItem(user)
            }
        }
    }

}
@Composable
fun FriendItem(user: User) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(text = user.name, fontSize = 18.sp)
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
fun MyProfileCard(user: User) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(170.dp)
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

            Text(text = user.name, fontSize = 18.sp)
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
    FriendScreen()
}

