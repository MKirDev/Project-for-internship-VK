package com.applicaton.internshipvk.presentation

import android.os.Bundle
import android.os.Parcelable
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.applicaton.internshipvk.R
import com.applicaton.internshipvk.databinding.FragmentInfoBinding
import com.bumptech.glide.Glide

private const val ARG_PARAM2 = "param2"

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null

    private val binding get() = _binding!!

    private var param: ProductModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param = it.getParcelable(ARG_PARAM2)
        }
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.slide_right)
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            title.text = param?.title
            description.text = param?.description
            Glide
                .with(imageView.context)
                .load(param?.images?.get(0))
                .into(imageView)
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_InfoFragment_to_ProductsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}