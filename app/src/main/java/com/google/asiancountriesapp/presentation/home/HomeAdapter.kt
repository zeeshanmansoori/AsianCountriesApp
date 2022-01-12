package com.google.asiancountriesapp.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.asiancountriesapp.databinding.FragmentHomeRcvBinding
import com.google.asiancountriesapp.domain.model.Country

class HomeAdapter(val onItemClick: (Country) -> Unit) :
    ListAdapter<Country, HomeAdapter.HomeViewHolder>(diffUtil) {


    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean =
                oldItem == newItem

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            FragmentHomeRcvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION && getItem(position) != null)
            holder.bind(getItem(position))
    }

    inner class HomeViewHolder(private val binding: FragmentHomeRcvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val data = getItem(position)
                    if (data != null)
                        onItemClick(data)
                }
            }

        }

        fun bind(country: Country) {
            binding.apply {
                Glide.with(countryFlag).load(country.flag).centerCrop().into(countryFlag)
                countryName.text = country.name.officialName
            }
        }
    }


}