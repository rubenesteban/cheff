package com.abeja.cheff.Screen


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.abeja.cheff.componets.ProgressBar
import com.abeja.cheff.model.Response

//import com.abeja.cheff.model.Response


@Composable
fun AddImageToDatabase(
    viewModel: ImageViewModel = hiltViewModel(),
    showSnackBar: (isImageAddedToDatabase : Boolean)-> Unit

){
    when (val addImageToDatabaseResponse = viewModel.addImageToDatabaseResponse){
        is Response.Loading -> ProgressBar()
        is Response.Success -> addImageToDatabaseResponse.data?.let { isImageAddedToDatabase->
            LaunchedEffect(isImageAddedToDatabase){
                showSnackBar(isImageAddedToDatabase)
            }

        }
        is Response.Failure -> print(addImageToDatabaseResponse.e)
    }
}
