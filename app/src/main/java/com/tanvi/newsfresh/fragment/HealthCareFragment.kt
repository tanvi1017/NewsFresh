package com.tanvi.newsfresh.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.HealthDataItem
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.adapter.HealthAdapter
import com.tanvi.newsfresh.databinding.FragmentHealthCareBinding

class HealthCareFragment : Fragment() {
    private lateinit var binding: FragmentHealthCareBinding
    val healthCareList: MutableList<HealthDataItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHealthCareBinding.inflate(layoutInflater)
        setAdapter()
        formData()
        return binding.root
    }

    fun setAdapter() {
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = HealthAdapter(healthCareList)
        binding.recyclerview.adapter = adapter

    }
    fun formData(){
        val health1 = HealthDataItem("i am ok", R.drawable.iv20)
        val health2 = HealthDataItem("i am ok",R.drawable.iv22)
        val health3 = HealthDataItem("i am ok",R.drawable.iv20)
        val health4 = HealthDataItem("i am ok",R.drawable.iv5)
        val health5 = HealthDataItem("i am ok",R.drawable.iv22)
        val health6 = HealthDataItem("i am ok",R.drawable.iv25)
        healthCareList.add(health1)
        healthCareList.add(health2)
        healthCareList.add(health3)
        healthCareList.add(health4)
        healthCareList.add(health5)
        healthCareList.add(health6)

    }
}



