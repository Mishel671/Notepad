package com.example.notepad.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notepad.models.AppNote
import java.security.AccessControlContext

@Database(entities = [AppNote::class], version = 1)
abstract class AppRoomDatabase: RoomDatabase() {
    abstract fun getAppRoomDao():AppRoomDao

    companion object{

        @Volatile
        private var database:AppRoomDatabase?=null

        @Synchronized
        fun getInstance(context: Context):AppRoomDatabase{
            return if(database==null){
                database = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "database"
                ).build()
                database as AppRoomDatabase
            }else database as AppRoomDatabase
        }
    }
}
