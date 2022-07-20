package com.mhs.androidboosterproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mhs.androidboosterproject.data.dao.StudentDao
import com.mhs.androidboosterproject.data.entity.Student

@Database(
    entities = [Student::class], version = 1
)
abstract class ClassDatabase :RoomDatabase() {
    abstract val studentDao : StudentDao

    companion object{
        private var INSTANCE : ClassDatabase ?= null

        fun getDatabase(context: Context):ClassDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    ClassDatabase::class.java,
                    "localClassDb",
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }
}