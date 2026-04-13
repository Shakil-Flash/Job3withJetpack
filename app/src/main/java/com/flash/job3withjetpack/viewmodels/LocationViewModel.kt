package com.flash.job3withjetpack.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.flash.job3withjetpack.repo.Repository

class LocationViewModel(private val repo: Repository) : ViewModel() {

    val locationUpdateResult = MutableLiveData<Pair<Boolean, String?>>()

    fun saveLocation(lat: Double, lng: Double) {
        repo.updateUserLocation(lat, lng) { success, message ->
            locationUpdateResult.postValue(success to message)
        }
    }
}