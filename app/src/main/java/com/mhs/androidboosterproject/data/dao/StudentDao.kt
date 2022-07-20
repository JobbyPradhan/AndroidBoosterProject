package com.mhs.androidboosterproject.data.dao

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
                LOWER(:query) == gender || '%' OR
                 LOWER(:query) == major || '%' OR
                 LOWER(rollNo) LIKE '%' || LOWER(:query)
    """)
    suspend fun searchStudentListing(query:String):List<Student>
}
