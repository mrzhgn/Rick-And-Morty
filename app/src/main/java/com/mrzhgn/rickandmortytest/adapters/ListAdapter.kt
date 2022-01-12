package com.mrzhgn.rickandmortytest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mrzhgn.rickandmortytest.R
import com.mrzhgn.rickandmortytest.databinding.ItemListBinding
import com.mrzhgn.rickandmortytest.ui.ListItem

class ListAdapter(
    private var personItemList: List<ListItem>,
    private val listener: ListAdapterListener
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemListBinding, listener: ListAdapterListener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        val itemListBinding = binding
        var listAdapterListener: ListAdapterListener = listener

        init {
            itemListBinding.root.clipToOutline = true
            itemListBinding.root.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val item: ListItem = personItemList.get(absoluteAdapterPosition)
            listAdapterListener.onItemClick(item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, listener)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val listItem: ListItem = personItemList[position]
        listItem.person.image.let {
            Glide.with(holder.itemListBinding.image.context)
                .load(it)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.itemListBinding.image)
        }
        holder.itemListBinding.name.text = listItem.person.name
        holder.itemListBinding.isAlive.setImageResource(when (listItem.person.status) {
            "Alive" -> R.drawable.circle_green
            else -> R.drawable.circle_red
        })
        holder.itemListBinding.status.text = "${listItem.person.status} - ${listItem.person.species}"
        holder.itemListBinding.location.text = listItem.person.locationName
        holder.itemListBinding.origin.text = listItem.person.originName
    }

    fun updateList(list: List<ListItem>) {
        personItemList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return personItemList.size
    }

    interface ListAdapterListener {
        fun onItemClick(person: ListItem?)
    }
}