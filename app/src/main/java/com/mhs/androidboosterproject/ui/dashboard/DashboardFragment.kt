package com.mhs.androidboosterproject.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.mhs.androidboosterproject.data.entity.TrainingClass
import com.mhs.androidboosterproject.databinding.FragmentDashboardBinding
import com.mhs.androidboosterproject.ui.dashboard.adapter.RecommendedCourseAdapter
import com.mhs.androidboosterproject.ui.insertData.CourseActivity
import com.mhs.androidboosterproject.viewModel.TrainingViewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val trainingViewModel : TrainingViewModel by activityViewModels()
    private lateinit var recommendedAdapter : RecommendedCourseAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecommendedRec()
        getRecommend()
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(requireContext(),CourseActivity::class.java))
        }
    }

    private fun initRecommendedRec() {
        recommendedAdapter = RecommendedCourseAdapter(arrayListOf())
        binding.recRecommendList.apply {
            setHasFixedSize(true)
            adapter = recommendedAdapter
        }
    }

    private fun getRecommend() {
        trainingViewModel.getRecommendedTraining().observe(viewLifecycleOwner){
            if(it.isNotEmpty())
                recommendedAdapter.setData(it as ArrayList<TrainingClass>)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}