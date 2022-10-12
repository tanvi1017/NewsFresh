package com.tanvi.newsfresh.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.TechnologyDataItem
import com.tanvi.newsfresh.adapter.SportAdapter
import com.tanvi.newsfresh.adapter.TechnologyAdapter
import com.tanvi.newsfresh.databinding.FragmentSportBinding
import com.tanvi.newsfresh.databinding.FragmentTechnologyBinding


class TechnologyFragment : Fragment() {
   private  lateinit var binding: FragmentTechnologyBinding
   val TechnologyList:MutableList<TechnologyDataItem> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTechnologyBinding.inflate(layoutInflater)
        setAdapter()
        FormData()
        return binding.root
    }
    fun setAdapter(){
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = TechnologyAdapter(TechnologyList)
        binding.recyclerview.adapter = adapter
    }
    fun FormData(){
      val Technology1 = TechnologyDataItem("i am good", R.drawable.iv20)
        val Technology2 = TechnologyDataItem("i am good",R.drawable.iv22)
        val Technology3 = TechnologyDataItem("i am good",R.drawable.iv5)
        val Technology4 = TechnologyDataItem("i am good",R.drawable.iv25)
        val Technology5 = TechnologyDataItem("i am good",R.drawable.iv21)
        TechnologyList.add(Technology1)
        TechnologyList.add(Technology2)
        TechnologyList.add(Technology3)
        TechnologyList.add(Technology4)
        TechnologyList.add(Technology5)


    }
}