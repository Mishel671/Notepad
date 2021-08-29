package com.example.notepad.database.firebase

import androidx.lifecycle.LiveData
import com.example.notepad.database.DatabaseRepository
import com.example.notepad.models.AppNote
import com.example.notepad.utilits.EMAIL
import com.example.notepad.utilits.PASSWORD
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class AppFirebaseRepository: DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()

    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener{ onFail(it.message.toString()) }
            }
    }

    override fun signOut() {
        mAuth.signOut()
    }
}