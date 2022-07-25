package com.mhs.androidboosterproject.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mhs.androidboosterproject.data.entity.Student
import com.mhs.androidboosterproject.databinding.FragmentHomeBinding
import com.mhs.androidboosterproject.ui.home.adapter.StudentAdapter
import com.mhs.androidboosterproject.ui.insertData.StudentActivity
import com.mhs.androidboosterproject.viewModel.StudentViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList : ArrayList<Student>

    private val viewModel : StudentViewModel by activityViewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupSearch()

        return root
    }

    private fun setupSearch() {
        viewModel.studentSearch.observe(viewLifecycleOwner){
            Log.i("TASFSAFA", "setupSearch: $it")
            getStudent(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentList = arrayListOf()
        initRec()
        getStudent("")
        onClick()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getStudent(s: String) {
        viewModel.getStudent(s).observe(viewLifecycleOwner){
            if(it.isNotEmpty()) {
                studentList = it as ArrayList<Student>
                studentAdapter.setData(studentList)
            }
        }
    }

    private fun initRec() {
        studentAdapter = StudentAdapter(studentList)
        binding.recStudentList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = studentAdapter
        }
    }

    private fun onClick() {
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(requireActivity(),StudentActivity::class.java))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}