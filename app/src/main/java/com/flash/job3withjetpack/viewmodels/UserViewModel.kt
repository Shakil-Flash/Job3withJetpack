package com.flash.job3withjetpack.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flash.job3withjetpack.data.User
import com.flash.job3withjetpack.repo.Repository

class UserViewModel(private val repo: Repository) : ViewModel() {

    val users = MutableLiveData<List<User>>()
    val currentUser = MutableLiveData<User?>()

    fun fetchUsers() {
        repo.getAllUsers { userList ->
            val currentId = repo.currentUserId()
            users.postValue(userList.filter { it.userId != currentId })
            currentUser.postValue(userList.find { it.userId == currentId })
        }
    }
}