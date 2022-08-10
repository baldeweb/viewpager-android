package com.wallace.viewpagerandroid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wallace.viewpagerandroid.databinding.ItemPageBinding
import java.util.ArrayList

class ListNameAdapter(private var listName: ArrayList<String>) : RecyclerView.Adapter<ListNameAdapter.ListNameViewHolder>() {
    private lateinit var binding: ItemPageBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNameViewHolder {
        binding = ItemPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListNameViewHolder()
    }

    override fun getItemCount() = listName.size

    override fun onBindViewHolder(holder: ListNameViewHolder, position: Int) {
        binding.tvwName.text = listName[position]
    }

    inner class ListNameViewHolder : RecyclerView.ViewHolder(binding.root)
}