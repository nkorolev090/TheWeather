package com.example.firebaseapi.recommendations.advice

import com.example.firebaseapi.recommendations.advice.models.AdviceDTO
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AdviceAPI @Inject constructor(private val db: FirebaseFirestore) {

    suspend fun getAdvice() = db
        .collection(COLLECTION_PATH)
        .get()
        .await()
        .documents
        .firstOrNull()
        ?.toObject<AdviceDTO>()

    suspend fun writeNewAdvice(advice: AdviceDTO) {
        db
            .collection(COLLECTION_PATH)
            .document()
            .set(advice)
            .await()
    }

    companion object {
        private const val COLLECTION_PATH = "AdviceTest"
    }
}