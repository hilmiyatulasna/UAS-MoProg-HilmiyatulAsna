package com.example.tugasandroid.ui.route.profile

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasandroid.data.local.LocalMovie
import com.example.tugasandroid.data.repository.MovieRepository
import com.example.tugasandroid.manager.ImageManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    repository: MovieRepository,
    private val imageManager: ImageManager
): ViewModel() {
    val movies = repository.getAllMovies()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            emptyList()
        )

    private val _profileUri = MutableStateFlow<String?>(null)
    val profileUri = _profileUri.asStateFlow()

    init {
        val path = imageManager.sharedPref.getString(ImageManager.PROFILE_PREFERENCES, null)
        _profileUri.update { path }
    }

    private val _isDialogShowing = MutableStateFlow(false)
    val isDialogShowing = _isDialogShowing.asStateFlow()

    fun setProfileUri(uri: Uri?) {
        if (uri == null) return
        val path = imageManager.saveUriToInternalStorage(uri, "${System.currentTimeMillis()}-profile.jpg")
        with (imageManager.sharedPref.edit()) {
            putString(ImageManager.PROFILE_PREFERENCES, path)
            apply()
        }
        _profileUri.value = path
    }

    fun showDialog() {
        _isDialogShowing.value = true
    }

    fun hideDialog() {
        _isDialogShowing.value = false
    }
}