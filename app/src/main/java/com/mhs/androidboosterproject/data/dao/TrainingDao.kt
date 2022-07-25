package com.mhs.androidboosterproject.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mhs.androidboosterproject.data.entity.Student
import com.mhs.androidboosterproject.data.entity.TrainingClass

@Dao
interface TrainingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClass(
        trainingClass: TrainingClass
    )

    @Query("SELECT * FROM TrainingClass ORDER BY rating DESC Limit 4 ")
    fun getRecommendedTrainingList():LiveData<List<TrainingClass>>

    @Query("""
        SELECT * 
        FROM TrainingClass
        where LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(course) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(tutorName) LIKE '%' || LOWER(:query) || '%' OR
                LOWER(rating) LIKE '%' || LOWER(:query)
                 
    """)
    fun searchTrainingClassListing(query:String): LiveData<List<TrainingClass>>
}