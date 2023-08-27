package com.abeja.cheff.Screen

import android.net.Uri

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.abeja.cheff.componets.ProgressBar
import com.abeja.cheff.model.Response


@Composable
fun AddImageToStorage(
    viewModel: ImageViewModel = hiltViewModel(),
    addImageToDatabase : (downloadUrl: Uri)->Unit
){
    when (val addImageToStorageResponse = viewModel.addImageToStorageResponse){
        is Response.Loading -> ProgressBar()
        is Response.Success -> addImageToStorageResponse.data?.let { downloadUrl ->
            LaunchedEffect(downloadUrl){
                addImageToDatabase(downloadUrl)
            }

        }
        is Response.Failure -> print(addImageToStorageResponse.e)
    }
}