package com.tanvi.newsfresh.util

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tanvi.newsfresh.service.AppRepository
import com.tanvi.newsfresh.viewmodel.NewsViewModel

class ViewModelProviderFactory(val app : Application ,private val appRepository: AppRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(app, appRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}