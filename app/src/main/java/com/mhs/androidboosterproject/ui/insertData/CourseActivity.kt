package com.mhs.androidboosterproject.ui.insertData

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker

import com.mhs.androidboosterproject.data.entity.TrainingClass
import com.mhs.androidboosterproject.databinding.ActivityClassBinding
import com.mhs.androidboosterproject.viewModel.TrainingViewModel

class CourseActivity : AppCompatActivity() {

    private lateinit var binding:ActivityClassBinding
    private val trainingViewModel : TrainingViewModel by viewModels()
    private var profileUri =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClick()
    }

    private fun onClick() {
        binding.btnRegister.setOnClickListener {
            setUpData()
        }
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.imgProfile.setOnClickListener {
            ImagePicker.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .createIntent { intent ->
                    startForProfileImageResult.launch(intent)
                }
        }
    }
    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    //Image Uri will not be null for RESULT_OK
                    val fileUri = data?.data!!

                    profileUri = fileUri.toString()
                    binding.imgProfile.setImageURI(fileUri)
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }


    private fun setUpData() {
        val name = binding.etName.text.toString()
        val studentCount = if(binding.etStudentCount.text.toString().isNullOrEmpty()){
            0
        }else
            binding.etStudentCount.text.toString().toInt()
        val rating = binding.ratingBar.rating
        val course = binding.spinnerCourse.selectedItem.toString()
        val desc = binding.etDesc.text.toString()
        val address = binding.etAddress.text.toString()
        val imgUri = profileUri
        val phone = binding.etPhNo.text.toString()
        val tutorName = binding.etTutorName.text.toString()

        val trainingClass = TrainingClass(null,name,imgUri,phone,address,rating,course,desc,tutorName,studentCount)
        trainingViewModel.insertTraining(trainingClass)
        finish()
    }
}