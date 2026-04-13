package com.flash.job3withjetpack.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flash.job3withjetpack.data.User
import com.flash.job3withjetpack.repo.Repository

class FriendListViewModel(private val repo: Repository) : ViewModel() {
    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList

    fun fetchUsers() {
        repo.getAllUsers { list ->
            _userList.postValue(list)

        }
    }
}