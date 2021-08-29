package com.example.notepad.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notepad.database.firebase.AppFirebaseRepository
import com.example.notepad.database.room.AppRoomDatabase
import com.example.notepad.database.room.AppRoomRepository
import com.example.notepad.utilits.REPOSITORY
import com.example.notepad.utilits.TYPE_FIREBASE
import com.example.notepad.utilits.TYPE_ROOM
import com.example.notepad.utilits.showToast

class StartFragmentViewModel(application: Application):AndroidViewModel(application) {
    private val mContext = application

    fun initDatabase(type:String, onSuccess:()->Unit){
        when(type){
            TYPE_ROOM ->{
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }

            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase({onSuccess},{ showToast(it)})
            }
        }
    }
}