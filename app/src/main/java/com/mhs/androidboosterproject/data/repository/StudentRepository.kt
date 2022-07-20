package com.mhs.androidboosterproject.data.repository

import android.app.Application
import com.mhs.androidboosterproject.AppConfig
import com.mhs.androidboosterproject.data.entity.Student
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StudentRepository(val application: Application) {
    val db = (application as AppConfig).database
    suspend fun getStudentList(query:String):Flow<List<Student>>{
        return flow {
            val studentList =db.studentDao.searchStudentListing("")
            emit(studentList)
        }
    }

    suspend fun insertStudent(student: Student){
        db.studentDao.insertStudent(student)
    }

}