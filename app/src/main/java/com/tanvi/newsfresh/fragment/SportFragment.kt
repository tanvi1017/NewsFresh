package com.tanvi.newsfresh.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanvi.newsfresh.R
import com.tanvi.newsfresh.SportDataItem
import com.tanvi.newsfresh.adapter.SportAdapter
import com.tanvi.newsfresh.databinding.FragmentScienceBinding
import com.tanvi.newsfresh.databinding.FragmentSportBinding


class SportFragment : Fragment() {
    private lateinit var binding: FragmentSportBinding
    val SportList:MutableList<SportDataItem> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentSportBinding.inflate(layoutInflater)
        formData()
        setAdapter()
        return binding.root
    }
     fun setAdapter(){
         binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
         val adapter = SportAdapter(SportList)
         binding.recyclerview.adapter = adapter
     }
    fun formData(){
        // baby use  and s small
     val  Sport1 = SportDataItem("i am good", R.drawable.iv20)
      val Sport3 = SportDataItem("i am good",R.drawable.iv22)
      val Sport4 = SportDataItem("i am good",R.drawable.iv22)
      val Sport5 = SportDataItem("i am good",R.drawable.iv22)
      val Sport6 = SportDataItem("i am good",R.drawable.iv22)
        SportList.add(Sport1)
        SportList.add(Sport3)
        SportList.add(Sport4)
        SportList.add(Sport5)
        SportList.add(Sport6)


    }

}