package com.mhs.androidboosterproject.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mhs.androidboosterproject.data.entity.Student
import com.mhs.androidboosterproject.data.repository.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StudentViewModel(application: Application):AndroidViewModel(application) {
    private val repository : StudentRepository by lazy { StudentRepository(application) }

    var studentSearch = MutableLiveData<String>()

    fun getStudent(query:String) :LiveData<List<Student>>{
        return repository.getStudentList(query)
    }

    fun getStudentById(id:Int) :LiveData<Student>{
        return repository.getStudentListById(id)
    }
    fun insertStudentForm(student:Student){
        viewModelScope.launch {
            repository.insertStudent(student)
        }
    }
}