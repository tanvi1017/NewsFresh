package com.tanvi.newsfresh.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.ScienceDataItem
import com.tanvi.newsfresh.adapter.ScienceAdapter
import com.tanvi.newsfresh.databinding.FragmentScienceBinding
import com.tanvi.newsfresh.databinding.ItemNewsBinding


class ScienceFragment : Fragment() {
private lateinit var binding: FragmentScienceBinding
val ScienceList:MutableList<ScienceDataItem> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     binding=FragmentScienceBinding.inflate(layoutInflater)
        setAdapter()
        formData()
        return binding.root
    }
    fun setAdapter(){
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = ScienceAdapter(ScienceList)
        binding.recyclerview.adapter = adapter

    }
    fun formData(){
       val Science1 =ScienceDataItem("i am ok", R.drawable.iv20)
        val Science2 =ScienceDataItem("i am ok",R.drawable.iv21)
        val Science3 = ScienceDataItem("i am ok",R.drawable.iv27)
        val Science4 = ScienceDataItem("i am ok",R.drawable.iv25)
        val Science5 = ScienceDataItem("i am ok",R.drawable.iv22)
        ScienceList.add(Science1)
        ScienceList.add(Science2)
        ScienceList.add(Science3)
        ScienceList.add(Science4)
        ScienceList.add(Science5)
    }

    }
