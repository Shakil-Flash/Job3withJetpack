package com.flash.job3withjetpack.repo

import com.flash.job3withjetpack.data.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Repository {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()


    fun registerUser(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val userId = result.user?.uid ?: return@addOnSuccessListener
                val userName = email.substringBefore("@")
                val user = User(
                    userId = userId,
                    userName = userName,
                    email = email
                )
                db.collection("users").document(userId).set(user)
                    .addOnSuccessListener {
                        onComplete(true, "Registration Successful")
                    }
                    .addOnFailureListener { e ->
                        onComplete(false, "Firestore Error: ${e.localizedMessage}")
                    }
            }
            .addOnFailureListener { e ->
                onComplete(false, "Auth Error: ${e.localizedMessage}")
            }
    }

    fun loginUser(email: String, password: String, onComplete: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                onComplete(true, "Welcome back!")
            }
            .addOnFailureListener { e ->
                onComplete(false, "Login Error: ${e.localizedMessage}")
            }
    }

    fun currentUserId(): String? = auth.currentUser?.uid

}