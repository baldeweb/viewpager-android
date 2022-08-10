package com.wallace.viewpagerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.wallace.viewpagerandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ListNameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerPager()
    }

    private fun initRecyclerPager() {
        val listName = arrayListOf(
            "AAA", "BBB", "CCC", "DDD", "EEE"
        )
        adapter = ListNameAdapter(listName)
        binding.rcvProposalPager.adapter = adapter

        binding.rcvProposalPager.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rcvProposalPager.addItemDecoration(PageIndicatorDecoration(
            colorActive = ContextCompat.getColor(this, R.color.teal_200),
            colorInactive = ContextCompat.getColor(this, R.color.purple_500))
        )
        PagerSnapHelper().attachToRecyclerView(binding.rcvProposalPager)
    }
}