package com.example.notepad.screens.add_new_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notepad.models.AppNote
import com.example.notepad.utilits.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewNoteViewModel(application:Application):AndroidViewModel(application) {
    fun insert(note:AppNote,onSucces:()->Unit) =
        viewModelScope.launch (Dispatchers.Main){
            REPOSITORY.insert(note){
                onSucces()
            }
        }
}