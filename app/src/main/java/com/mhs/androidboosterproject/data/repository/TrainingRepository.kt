package com.mhs.androidboosterproject.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mhs.androidboosterproject.AppConfig
import com.mhs.androidboosterproject.data.entity.Student
import com.mhs.androidboosterproject.data.entity.TrainingClass

class TrainingRepository(application: Application) {
    val db = (application as AppConfig).database


    fun getTrainingList(query:String): LiveData<List<TrainingClass>> {
        val trainingList =db.trainingDao.searchTrainingClassListing(query)
        return trainingList
    }
    fun getRecommendTrainingList(): LiveData<List<TrainingClass>> {
        val trainingList =db.trainingDao.getRecommendedTrainingList()
        return trainingList
    }


    suspend fun insertStudent(trainingClass: TrainingClass){
        db.trainingDao.insertClass(trainingClass)
    }
}