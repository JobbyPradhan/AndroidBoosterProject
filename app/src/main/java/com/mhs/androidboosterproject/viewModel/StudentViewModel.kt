package com.mhs.androidboosterproject.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhs.androidboosterproject.data.entity.Student
import com.mhs.androidboosterproject.data.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel(application: Application):AndroidViewModel(application) {
    private val repository : StudentRepository by lazy { StudentRepository(application) }


    fun insertStudentForm(student:Student){
        viewModelScope.launch {
            repository.insertStudent(student)
        }
    }
}