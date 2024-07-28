package com.example.tugasandroid.manager

import android.content.Context
import android.net.Uri
import java.io.FileOutputStream

class ImageManager(
    private val context: Context
) {
    val sharedPref = context.getSharedPreferences(PROFILE_PREFERENCES, Context.MODE_PRIVATE)

    fun saveUriToInternalStorage(uri: Uri, fileName: String): String? {
        try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val outputStream: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)

            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            return context.getFileStreamPath(fileName).absolutePath
        } catch (e: Exception) {
            // Handle exceptions (e.g., IOException)
            e.printStackTrace()
            return null
        }
    }

    companion object {
        const val PROFILE_PREFERENCES = "profile_image"
    }
}