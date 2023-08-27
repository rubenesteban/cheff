package com.abeja.cheff.repository

import android.net.Uri
import com.abeja.cheff.model.Response


typealias AddImageToStorageResponse = Response<Uri>
typealias AddImageUrlToFirestoreResponse = Response<Boolean>
typealias GetImageFromFirestoreResponse = Response<String>



interface ImageRepository{
    suspend fun addImageToFirebaseStorage(imageUri: Uri): AddImageToStorageResponse
    suspend fun addImageUrlToFirestore(download: Uri): AddImageUrlToFirestoreResponse
    suspend fun getImageUrlFromFirestore(): GetImageFromFirestoreResponse
}

