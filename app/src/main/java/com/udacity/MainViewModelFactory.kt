package com.udacity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MainViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModelFactory::class.java)) {
            return MainViewModelFactory() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}