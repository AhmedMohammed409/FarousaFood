package com.example.farousa.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.farousa.data.order
import com.example.farousa.databinding.FragmentDetailBinding
import com.google.ar.core.Config
import io.github.sceneview.ar.node.ArModelNode
import io.github.sceneview.ar.node.PlacementMode
import io.github.sceneview.math.Position

class DetailFragment : Fragment() {
 private lateinit var binding: FragmentDetailBinding
    private lateinit var modelNode: ArModelNode
    private val args:DetailFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding= FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.arsceneview.apply {
            this.lightEstimationMode = Config.LightEstimationMode.DISABLED
        }

        //intial
        createModel(args.name)
        binding.arsceneview.addChild(modelNode)
        binding.btnOrder.setOnClickListener {
            Toast.makeText(requireContext(),"add it items",Toast.LENGTH_SHORT).show()
            order += args.name+"\n"
        }

        binding.back.setOnClickListener {
            findNavController().navigateUp() }

       binding.btnInstall.setOnClickListener {
           placeModel() }

    }

    private fun createModel(name:String){
        modelNode = ArModelNode(binding.arsceneview.engine, PlacementMode.INSTANT).apply {
            // Asynchronously loading a 3D model (GLB file)
            loadModelGlbAsync(
                glbFileLocation = "models/${name}.glb",
                scaleToUnits = 1f,
                centerOrigin = Position(-0.5f)
            ) {
                // Callback when the model is loaded
                binding.arsceneview.planeRenderer.isVisible = true
//                val materialInstance = it.materialInstances[0]
            }
// Callback when the anchor for the model changes
//            onAnchorChanged = {
//// Making a UI element (possibly a button) gone if the anchor is not null
//                binding.place.isGone = it != null
//            }
        }
    }
    private fun placeModel() {
// Anchoring the AR Model Node to the current position
        modelNode.anchor()
// Making the plane renderer invisible
        binding.arsceneview.planeRenderer.isVisible = false
    }

}