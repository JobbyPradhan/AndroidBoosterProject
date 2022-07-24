package com.mhs.androidboosterproject.ui.insertStudent

import android.app.Activity
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker
import com.mhs.androidboosterproject.data.entity.Student
import com.mhs.androidboosterproject.databinding.ActivityStudentBinding
import com.mhs.androidboosterproject.viewModel.StudentViewModel

class StudentActivity : AppCompatActivity() {

    private var mProfileUri:Uri ?=null
    private val viewModel : StudentViewModel by viewModels()
    private lateinit var binding:ActivityStudentBinding
    private var isEdit = false
    private var id:Int ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isEdit = intent.getBooleanExtra("Edit",false)
        if(isEdit){
            id = intent.getIntExtra("id",0)
            getStudent(id!!)
        }
        onClick()
    }

    private fun getStudent(id: Int) {
        viewModel.getStudentById(id).observe(this){
                setUpData(it!!)
        }
    }

    private fun setUpData(it: Student) {
        setUpSpinnerYear(it.year)
        setUpMajor(it.major)
        setUpGender(it.gender)
        binding.apply {
            etName.setText(it.name)
            etAddress.setText(it.address)
            etAge.setText(it.age.toString())
            etPhNo.setText(it.contactNumber)
            etRollNo.setText(it.rollNo)
            mProfileUri = Uri.parse(it.img)
            imgProfile.setImageURI(Uri.parse(it.img))
        }
    }

    private fun setUpGender(gender: String) {
        if(gender == "Male"){
            binding.rdoMale.isChecked = true
        }else
            binding.rdoFemale.isChecked = true
    }

    private fun setUpMajor(major: String) {
        if(major == "CS"){
            binding.spinnerMajor.setSelection(0)
        }else
            binding.spinnerMajor.setSelection(1)
    }

    private fun setUpSpinnerYear(year: String) {
        when(year){
            "First Year"->{binding.spinnerYear.setSelection(0)}
            "Second Year"->{binding.spinnerYear.setSelection(1)}
            "Third Year"->{binding.spinnerYear.setSelection(2)}
            "Fourth Year"->{binding.spinnerYear.setSelection(3)}
            else->{binding.spinnerYear.setSelection(4)}
        }

    }

    private fun onClick() {
        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.btnRegister.setOnClickListener {
            upsertStudent()
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

                    mProfileUri = fileUri
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

    private fun upsertStudent() {
        val name = binding.etName.text.toString().trim()
        val age = binding.etAge.text.toString().trim()
        val year = binding.spinnerYear.selectedItem.toString()
        val major = binding.spinnerMajor.selectedItem.toString()
        val gender = if(binding.rdoFemale.isChecked) "Female" else "Male"
        val phoneNumber = binding.etPhNo.text.toString().trim()
        val address = binding.etAddress.text.toString().trim()
        val rollNo = binding.etRollNo.text.toString().trim()
        val student = Student(
            id,name,rollNo,year,mProfileUri.toString(),major, age.toInt(),gender,phoneNumber,address
        )
        viewModel.insertStudentForm(student)
    }
}