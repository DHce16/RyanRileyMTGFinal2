package com.example.ryanrileymtgfinal2.view.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ryanrileymtgfinal2.R
import com.example.ryanrileymtgfinal2.databinding.BoosterListItemBinding
import com.example.ryanrileymtgfinal2.model.BoosterNode

// adapter for recyclerView for BoosterList
class BoosterListPageAdapter(
    private val boosterList: MutableList<BoosterNode> = mutableListOf(),
    private val openBoosterDetails: (BoosterNode) -> Unit
) : RecyclerView.Adapter<BoosterListPageAdapter.BoosterViewHolder>() {

    fun setBoosterList(newList: List<BoosterNode>, updateList: Boolean) {
        if (updateList) {
            boosterList.addAll(newList)
            notifyItemRangeChanged(0, itemCount)
        } else {
            boosterList.clear()
            boosterList.addAll(newList)
            notifyItemRangeChanged(0, itemCount)
        }
    }

    inner class BoosterViewHolder(
        private val binding: BoosterListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BoosterNode) {
            binding.tvBoosterListName.text = data.name
            Glide.with(binding.ivBoosterListImage)
                .load(R.drawable.magic_icon)
                .into(binding.ivBoosterListImage)

            binding.btnBoosterListDetails.setOnClickListener {
                openBoosterDetails(data!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BoosterViewHolder(
            BoosterListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BoosterViewHolder, position: Int) {
        holder.onBind(boosterList[position])
    }

    override fun getItemCount(): Int = boosterList.size
}