package com.example.volleyballonline

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val username = MutableLiveData<String>()

    fun setUserName(name: String) {
        username.value = name
    }

    fun getUserName() = username
}