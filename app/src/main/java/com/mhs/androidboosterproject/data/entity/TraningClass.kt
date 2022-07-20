package com.mhs.androidboosterproject.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrainingClass(
    @PrimaryKey (autoGenerate = true)
    val id :Int ?= null,
    val name:String,
    val address:String,
    val rating :Int,
    val description :String,
    val tutorName :String,
    val studentCount :Int
)
