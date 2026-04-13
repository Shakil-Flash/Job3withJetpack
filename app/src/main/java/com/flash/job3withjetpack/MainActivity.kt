package com.flash.job3withjetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.flash.job3withjetpack.repo.Repository
import com.flash.job3withjetpack.ui.LoginScreen
import com.flash.job3withjetpack.ui.ShareLocation
import com.flash.job3withjetpack.ui.MapScreen
import com.flash.job3withjetpack.ui.FriendScreen
import com.flash.job3withjetpack.ui.theme.Job3withJetpackTheme
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isLoggedIn by remember {
                mutableStateOf(FirebaseAuth.getInstance().currentUser != null)
            }
            var currentScreen by remember { mutableStateOf("friends") }
            var selectedLocation by remember { mutableStateOf<LatLng?>(null) }
            var selectedUserName by remember { mutableStateOf<String?>(null) }

            Job3withJetpackTheme {
                if (!isLoggedIn) {
                    LoginScreen(onLoginSuccess = { 
                        isLoggedIn = true 
                        currentScreen = "friends"
                    })
                } else {
                    when (currentScreen) {
                        "friends" -> FriendScreen(
                            onGetLocationClick = {
                                currentScreen = "share"
                            },
                            onFriendClick = { lat, lng, name ->
                                selectedLocation = LatLng(lat, lng)
                                selectedUserName = name
                                currentScreen = "map"
                            },
                            onLogout = {
                                Repository().logoutUser()
                                isLoggedIn = false
                            }
                        )
                        "share" -> ShareLocation(onShowMap = { lat, lng, name ->
                            selectedLocation = LatLng(lat, lng)
                            selectedUserName = name
                            currentScreen = "map"
                        })
                        "map" -> MapScreen(selectedLocation, selectedUserName, onBack = {
                            currentScreen = "friends"
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Job3withJetpackTheme {
        Greeting("Android")
    }
}