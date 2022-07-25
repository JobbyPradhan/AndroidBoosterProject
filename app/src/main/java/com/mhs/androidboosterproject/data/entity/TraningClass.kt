package com.mhs.androidboosterproject.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrainingClass(
    @PrimaryKey (autoGenerate = true)
    val id :Int ?= null,
    val name:String,
    val url:String,
    val phoneNumber:String,
    val address:String,
    val rating :Float,
    val course:String,
    val description :String,
    val tutorName :String,
    val studentCount :Int
)
