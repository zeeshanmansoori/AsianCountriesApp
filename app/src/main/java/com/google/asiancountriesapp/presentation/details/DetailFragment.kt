package com.google.asiancountriesapp.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.asiancountriesapp.R
import com.google.asiancountriesapp.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val detailViewModel: DetailViewModel by viewModels()


    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentDetailsBinding.bind(view)

        detailViewModel.data.observe(viewLifecycleOwner) { country ->
            binding.apply {
                commonName.text = country.name.commonName
                officialName.text = country.name.officialName
                Glide.with(countryFlag).load(country.flag).into(countryFlag)
                language.text = country.languages
                border.text = country.borders
                region.text = country.region
                subRegion.text = country.subRegion
                capital.text = country.capital
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}