package com.example.notepad.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notepad.utilits.REPOSITORY

class MainFragmentViewModel(application:Application):AndroidViewModel(application){
    val allNotes = REPOSITORY.allNotes
}