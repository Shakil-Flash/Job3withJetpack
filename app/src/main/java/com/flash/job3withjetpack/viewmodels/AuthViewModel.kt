package com.flash.job3withjetpack.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flash.job3withjetpack.repo.Repository

class AuthViewModel(private val repo: Repository): ViewModel() {

    val loginResult = MutableLiveData<Pair<Boolean, String?>>()
    val registerResult = MutableLiveData<Pair<Boolean, String?>>()

    fun login(email: String, password: String) {
        repo.loginUser(email, password) { success, message ->
            loginResult.postValue(success to message)
        }
    }

    fun register(email: String, password: String) {
        repo.registerUser(email, password) { success, message ->
            registerResult.postValue(success to message)
        }
    }
}