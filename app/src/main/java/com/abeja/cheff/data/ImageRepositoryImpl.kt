package com.abeja.cheff.data

import android.net.Uri
import com.abeja.cheff.model.Response
import com.abeja.cheff.repository.AddImageToStorageResponse
import com.abeja.cheff.repository.AddImageUrlToFirestoreResponse
import com.abeja.cheff.repository.GetImageFromFirestoreResponse
import com.abeja.cheff.repository.ImageRepository
import com.abeja.cheff.util.Constants.CREATED_AT
import com.abeja.cheff.util.Constants.IMAGES
import com.abeja.cheff.util.Constants.IMAGE_NAME
import com.abeja.cheff.util.Constants.UID
import com.abeja.cheff.util.Constants.URL
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await

import javax.inject.Inject


class ImageRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val db: FirebaseFirestore
): ImageRepository {
    override suspend fun addImageToFirebaseStorage(imageUri: Uri):AddImageToStorageResponse {
        return try{
            val downloadUrl = storage.reference.child(IMAGES).child(IMAGE_NAME)
                .putFile(imageUri).await()
                .storage.downloadUrl.await()
            Response.Success(downloadUrl)
        }
        catch (e:Exception){
            Response.Failure(e)
        }
    }

    override suspend fun addImageUrlToFirestore(download: Uri): AddImageUrlToFirestoreResponse {
        return try{
            db.collection(IMAGES).document(UID).set(mapOf(
                URL to download,
                CREATED_AT to FieldValue.serverTimestamp()
            )).await()
            Response.Success(true)
        }
        catch (e:Exception){
            Response.Failure(e)
        }
    }

    override suspend fun getImageUrlFromFirestore(): GetImageFromFirestoreResponse {
        return try{
            val imageUrl = db.collection(IMAGES).document(UID).get().await().getString(URL)
            Response.Success(imageUrl)

        }
        catch (e: Exception){
            Response.Failure(e)
        }
    }

}
