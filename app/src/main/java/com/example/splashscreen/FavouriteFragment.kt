package com.example.splashscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.splashscreen.databinding.FragmentFavouriteBinding


class FavouriteFragment : Fragment() {

    private var _binding : FragmentFavouriteBinding? = null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val favouriteViewModel = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvFavRec.layoutManager = LinearLayoutManager(context)
        favouriteViewModel.getAllFavRecords().observe(viewLifecycleOwner){
            binding.rvFavRec.adapter = FavouriteAdaptor(it)
        }
        //    val textView: TextView = binding.textFavourite
        //    favouriteViewModel.text.observe(viewLifecycleOwner) {
        //        textView.text = it
        //    }
        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
