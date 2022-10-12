package com.tanvi.newsfresh.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.EntertainmentDataItem
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.adapter.EntertainmentAdapter
import com.tanvi.newsfresh.databinding.FragmentEntertainmentBinding

class EntertainmentFragment : Fragment() {
   private lateinit var binding: FragmentEntertainmentBinding
   var entertainmentList:MutableList<EntertainmentDataItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntertainmentBinding.inflate(layoutInflater)
            //prepare list to diaplay in recyclervierw
        formData()
        // setadaptyer
       SetAdapter()
        return binding.root
    }
    fun SetAdapter(){

        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = EntertainmentAdapter(entertainmentList)
        binding.recyclerview.adapter = adapter

    }
    fun formData(){
       val entertainment1 = EntertainmentDataItem("i am ok", R.drawable.iv5 )
        val entertainment2 = EntertainmentDataItem("i am ok",R.drawable.iv22)
        val entertainment3 = EntertainmentDataItem("i am ok",R.drawable.iv22)
        val entertainment4 = EntertainmentDataItem("i am ok",R.drawable.iv22)
        val entertainment5 = EntertainmentDataItem("i am ok",R.drawable.iv22)
        val entertainment6 = EntertainmentDataItem("i am ok",R.drawable.iv22)
        val entertainment7 = EntertainmentDataItem("i am ok",R.drawable.iv22)
        entertainmentList.add(entertainment1)
        entertainmentList.add(entertainment2)
        entertainmentList.add(entertainment3)
        entertainmentList.add(entertainment4)
        entertainmentList.add(entertainment5)
        entertainmentList.add(entertainment6)
        entertainmentList.add(entertainment7)
    }
    }
