package com.google.asiancountriesapp.presentation.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.asiancountriesapp.R
import com.google.asiancountriesapp.databinding.FragmentHomeBinding
import com.google.asiancountriesapp.domain.model.Country
import com.google.asiancountriesapp.utils.Resource
import com.google.asiancountriesapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.net.UnknownHostException

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        val adapter = HomeAdapter { country ->

            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                    country
                )
            )
        }

        binding.recyclerview.apply {
            setHasFixedSize(true)
            this.adapter = adapter
        }

        subscribeObservers(adapter)
        setHasOptionsMenu(true)
    }


    private fun subscribeObservers(adapter: HomeAdapter) {
        homeViewModel.countries.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Success -> {
                    displayProgressBar(false)
                    setData(adapter, state.data)
                }
                is Resource.Loading -> {
                    displayProgressBar(true)
                }
                is Resource.Error -> {
                    displayProgressBar(false)
                    setData(adapter, state.data)
                    displayError(state.message)
                }
            }

        }
    }

    private fun displayError(message: Exception?) {

        if (message != null && message is UnknownHostException) {
            showToast(
                requireContext(), "network error\n" + "loaded data from cache"
            )
        }
    }

    private fun displayProgressBar(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    private fun setData(adapter: HomeAdapter, data: List<Country>?) {
        adapter.submitList(data)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clear_action -> showDialog()
        }
        return super.onOptionsItemSelected(item)

    }


    private fun showDialog() {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setMessage("Do you really want to clear the Database/Cache")
            setNegativeButton(
                "No"
            ) { dialog, _ -> dialog.dismiss() }

            setPositiveButton(
                "Yes"
            ) { dialog, _ ->
                dialog.dismiss()
                homeViewModel.setStateEvent(HomeStateEvent.ClearDataBaseEvent)
                showToast(requireContext(), "operation completed")
            }
        }.show()
    }
}