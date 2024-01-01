package com.example.farousa.fragments.show

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.farousa.data.listOfFood
import com.example.farousa.data.order
import com.example.farousa.databinding.FragmentShowBinding


class ShowFragment : Fragment() {

private lateinit var binding: FragmentShowBinding
    private val adapterFood: CutomerAdapter by lazy {
        CutomerAdapter(listOfFood, onItemClicked = {
            findNavController().navigate(
                ShowFragmentDirections.actionShowFragmentToDetailFragment(it.imageId,it.modelName.toString()))
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentShowBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.inFood.textView.text="Food"
        binding.inFood.recyclerviewCategories.adapter=adapterFood

        binding.btnShowOrder.setOnClickListener {
            if (order.isEmpty()){Toast.makeText(requireContext(), "Not order",Toast.LENGTH_SHORT).show()}
            else {Toast.makeText(requireContext(), order,Toast.LENGTH_LONG).show()}
        }

    }

}