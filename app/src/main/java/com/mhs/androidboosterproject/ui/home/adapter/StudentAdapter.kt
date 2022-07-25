package com.mhs.androidboosterproject.ui.home.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhs.androidboosterproject.data.entity.Student
import com.mhs.androidboosterproject.databinding.ItemStudentLyBinding
import com.mhs.androidboosterproject.ui.insertData.StudentActivity

class StudentAdapter(
    private var studentList : ArrayList<Student>
): RecyclerView.Adapter<StudentAdapter.StudentHolder>() {

    fun setData(studentList: ArrayList<Student>)
    {
        this.studentList=studentList
        notifyDataSetChanged()
    }
    inner class StudentHolder(private val binding: ItemStudentLyBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(student: Student) {
            binding.tvName.text=student.name
            binding.tvRollNo.text = student.rollNo
            binding.tvYear.text = student.year
            binding.imgItem.setImageURI(Uri.parse(student.img))
            binding.imgBtnEdit.setOnClickListener {
                val intent = Intent(itemView.context,StudentActivity::class.java)
                intent.putExtra("Edit",true)
                intent.putExtra("id",student.id)
                itemView.context.startActivity(intent)
            }
           /* binding.root.setOnClickListener {
                val intent = Intent(itemView.context,DetailActivity::class.java)
                intent.putExtra("name",student.name)
                intent.putExtra("desc",student.desc)
                intent.putExtra("img",student.profile)
                itemView.context.startActivity(intent)
            }*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        return StudentHolder(
            ItemStudentLyBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        holder.bind(studentList[position])
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}