package com.mhs.androidboosterproject.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey (autoGenerate = true)
    val id :Int ?= null,
    val name :String,
    val rollNo :String,
    val year:String,
    val img :String,
    val major:String,
    val age:Int,
    val gender:String,
    val contactNumber:String,
    val address:String
)
//val courseId :Int
