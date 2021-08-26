package com.example.notepad.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepad.models.AppNote
import com.example.notepad.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteFragmentViewModel(application: Application): AndroidViewModel(application){
    fun delete(note:AppNote, onSucces:()->Unit) =
        viewModelScope.launch(Dispatchers.Main){
            REPOSITORY.delete(note){
                onSucces()
            }
        }
}