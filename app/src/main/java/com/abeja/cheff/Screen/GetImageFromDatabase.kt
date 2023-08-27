package com.abeja.cheff.Screen


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.abeja.cheff.componets.ProgressBar
import com.abeja.cheff.model.Response


@Composable
fun GetImageFromDatabase(
    viewModel: ImageViewModel = hiltViewModel(),
    createImageContent: @Composable (imageUrl: String)-> Unit
){
    when (val getImageFromDatabaseResponse = viewModel.addImageFromDatabaseResponsse){
        is Response.Loading -> ProgressBar()
        is Response.Success -> getImageFromDatabaseResponse.data?.let { imageUrl->
            createImageContent(imageUrl)

        }
        is Response.Failure -> print(getImageFromDatabaseResponse.e)
    }


}