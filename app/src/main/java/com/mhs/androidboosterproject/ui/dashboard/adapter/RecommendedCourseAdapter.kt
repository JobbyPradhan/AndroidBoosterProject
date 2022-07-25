package com.mhs.androidboosterproject.ui.dashboard.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhs.androidboosterproject.data.entity.Student
import com.mhs.androidboosterproject.data.entity.TrainingClass
import com.mhs.androidboosterproject.databinding.ItemLayoutBinding

class RecommendedCourseAdapter(
    private var recommendedList:ArrayList<TrainingClass>
    ):RecyclerView.Adapter<RecommendedCourseAdapter.CourseHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun setData(recommendedList: ArrayList<TrainingClass>)
    {
        this.recommendedList=recommendedList
        notifyDataSetChanged()
    }
      inner class CourseHolder(private val binding:ItemLayoutBinding):RecyclerView.ViewHolder(binding.root){
          fun bind(trainingClass: TrainingClass){
              binding.apply {
                  tvCourse.text = trainingClass.course
                  tvName.text = trainingClass.name
                  tvDesc.text = trainingClass.description
                  tvRating.text = trainingClass.rating.toString()
                  imgItem.setImageURI(Uri.parse(trainingClass.url))
                  imgBtnEdit.setOnClickListener {

                  }
              }
          }
      }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        return CourseHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {
        holder.bind(recommendedList[position])
    }

    override fun getItemCount(): Int {
       return recommendedList.size
    }


}