package com.rafsan.impressiontracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafsan.impressiontracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // initialize an instance of linear layout manager
        val layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        ).apply {
            // specify the layout manager for recycler view
            binding.recylerview.layoutManager = this
        }

        // finally, data bind the recycler view with adapter
        ItemAdapter().apply {
            binding.recylerview.adapter = this
        }


        // initialize an instance of divider item decoration
        DividerItemDecoration(
            this, // context
            layoutManager.orientation
        ).apply {
            // add divider item decoration to recycler view
            // this will show divider line between items
            binding.recylerview.addItemDecoration(this)
        }
    }
}