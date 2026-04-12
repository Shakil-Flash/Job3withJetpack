package com.flash.job3withjetpack.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.flash.job3withjetpack.repo.Repository
import com.flash.job3withjetpack.viewmodels.AuthViewModel

class AuthViewModelFactory(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}