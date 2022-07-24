package com.mhs.androidboosterproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mhs.androidboosterproject.data.entity.Student

@Dao
interface StudentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(
        student: Student
    )

    @Query("""
        SELECT * 
        FROM Student
        where LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(rollNo) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(year) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(gender) LIKE '%' || LOWER(:query) || '%' OR
                 LOWER(:query) ==  LOWER(major)
                 
    """)
    fun searchStudentListing(query:String): LiveData<List<Student>>

    @Query("""
        SELECT * 
        FROM Student
        where id == :id
    """)
    fun getStudentById(id:Int): LiveData<Student>
}
