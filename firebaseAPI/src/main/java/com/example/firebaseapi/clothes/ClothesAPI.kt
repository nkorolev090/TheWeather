package com.example.firebaseapi.clothes

import com.example.firebaseapi.clothes.models.ClothesDTO
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import jakarta.inject.Inject
import kotlinx.coroutines.tasks.await

class ClothesAPI @Inject constructor(private val db: FirebaseFirestore) {

    suspend fun getClothesRecommendation() = db
        .collection(COLLECTION_PATH)
        .get()
        .await()
        .documents
        .map { it.toObject<ClothesDTO>() }

    suspend fun writeNewClothes(clothes: ClothesDTO) {
        db
            .collection(COLLECTION_PATH)
            .document()
            .set(clothes)
            .await()
    }

    companion object {
        private const val COLLECTION_PATH = "ClothesTest"
    }
}