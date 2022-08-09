package com.example.ryanrileymtgfinal2.view.controller

//import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ryanrileymtgfinal2.R
import com.example.ryanrileymtgfinal2.databinding.CardListItemBinding
import com.example.ryanrileymtgfinal2.model.CardData
import com.example.ryanrileymtgfinal2.utils.httpConvert
//import com.squareup.picasso.Picasso

//adapter for recyclerView for cardList
class CardListPageAdapter (
    private val cardList: MutableList<CardData> = mutableListOf(),
    private val openCardDetails: (CardData) -> Unit
) : RecyclerView.Adapter<CardListPageAdapter.CardViewHolder>() {

    fun setCardList(newList: List<CardData>, updateList: Boolean) {
        if (updateList) {
            cardList.addAll(newList)
            notifyItemRangeChanged(0, itemCount)
        } else {
            cardList.clear()
            cardList.addAll(newList)
            notifyItemRangeChanged(0, itemCount)
        }
    }

    inner class CardViewHolder(
        private val binding: CardListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: CardData) {
//            Picasso.get()
            Glide.with(binding.ivCardListImage)
                .load(data.imageUrl?.httpConvert())
                .placeholder(R.drawable.magic_icon)
                .into(binding.ivCardListImage)

//            Log.d("Tag", "${data.imageUrl?.httpConvert()}")
            binding.root.setOnClickListener {
                openCardDetails(data!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CardViewHolder(
            CardListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.onBind(cardList[position])
    }

    override fun getItemCount() = cardList.size
}