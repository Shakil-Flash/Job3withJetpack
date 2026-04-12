package com.flash.job3withjetpack.data

data class User(
    val userId: String = "",
    val email: String = "",
    val userName: String = "",
    val lat: Double? = null,
    val lng: Double? = null
)
