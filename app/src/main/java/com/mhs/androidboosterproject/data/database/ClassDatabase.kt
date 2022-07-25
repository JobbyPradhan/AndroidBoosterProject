package com.mhs.androidboosterproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mhs.androidboosterproject.data.dao.StudentDao
import com.mhs.androidboosterproject.data.dao.TrainingDao
import com.mhs.androidboosterproject.data.entity.Student
import com.mhs.androidboosterproject.data.entity.TrainingClass

@Database(
    entities = [Student::class,TrainingClass::class], version = 1
)
abstract class ClassDatabase :RoomDatabase() {
    abstract val studentDao : StudentDao
    abstract val trainingDao : TrainingDao

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