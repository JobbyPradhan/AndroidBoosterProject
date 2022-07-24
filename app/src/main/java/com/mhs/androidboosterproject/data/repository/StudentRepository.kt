package com.mhs.androidboosterproject.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.mhs.androidboosterproject.AppConfig
import com.mhs.androidboosterproject.data.entity.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class StudentRepository(val application: Application) {
    val db = (application as AppConfig).database

    fun getStudentList(query:String): LiveData<List<Student>> {
            val studentList =db.studentDao.searchStudentListing(query)
            return studentList
    }

    fun getStudentListById(id:Int): LiveData<Student>{
        val student =db.studentDao.getStudentById(id)
        return student
    }

    suspend fun insertStudent(student: Student){
        db.studentDao.insertStudent(student)
    }

}