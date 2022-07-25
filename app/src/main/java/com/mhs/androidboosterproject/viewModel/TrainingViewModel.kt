package com.mhs.androidboosterproject.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mhs.androidboosterproject.data.entity.TrainingClass
import com.mhs.androidboosterproject.data.repository.TrainingRepository
import kotlinx.coroutines.launch

class TrainingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository : TrainingRepository by lazy { TrainingRepository(application) }

    fun insertTraining(trainingClass: TrainingClass) = viewModelScope.launch {
        repository.insertStudent(trainingClass)
    }

    fun getTrainingList(query:String) : LiveData<List<TrainingClass>>{
        return repository.getTrainingList(query)
    }
    fun getRecommendedTraining() : LiveData<List<TrainingClass>>{
        return repository.getRecommendTrainingList()
    }
}