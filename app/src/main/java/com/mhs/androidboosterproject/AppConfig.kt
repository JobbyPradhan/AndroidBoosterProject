package com.mhs.androidboosterproject

import android.app.Application
import com.mhs.androidboosterproject.data.database.ClassDatabase

class AppConfig : Application() {

    lateinit var database: ClassDatabase
    override fun onCreate() {
        super.onCreate()
        database = ClassDatabase.getDatabase(applicationContext)
    }
}