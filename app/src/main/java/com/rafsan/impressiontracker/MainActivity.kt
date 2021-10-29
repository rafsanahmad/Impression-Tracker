package com.rafsan.impressiontracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rafsan.impressiontracker.adapter.ItemAdapter
import com.rafsan.impressiontracker.data.ListItem
import com.rafsan.impressiontracker.databinding.ActivityMainBinding
import com.rafsan.impressiontracker.tracker.ImpressionTracker
import com.rafsan.impressiontracker.tracker.VisibleRows

class MainActivity : AppCompatActivity() {

    private lateinit var listData: ArrayList<ListItem>
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var binding: ActivityMainBinding
    private var tracker: ImpressionTracker? = null
    private val TAG: String = MainActivity::class.java.getName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createDummyList()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        itemAdapter = ItemAdapter(this)
        itemAdapter.setListData(listData)
        // initialize an instance of linear layout manager
        val layoutManager = LinearLayoutManager(
            this,
            RecyclerView.VERTICAL,
            false
        ).apply {
            // specify the layout manager for recycler view
            binding.recylerview.layoutManager = this
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

        binding.recylerview.apply {
            adapter = itemAdapter
            this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleStateFinal = VisibleRows(
                        layoutManager.findFirstCompletelyVisibleItemPosition(),
                        layoutManager.findLastCompletelyVisibleItemPosition()
                    )
                    tracker?.postViewEvent(visibleStateFinal)
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        tracker = ImpressionTracker()
        tracker?.startTracking { visibleRows: VisibleRows? ->
            this.onTrackViewResponse(visibleRows)
        }
    }

    override fun onPause() {
        super.onPause()
        tracker?.stopTracking()
    }

    private fun onTrackViewResponse(visibleRows: VisibleRows?) {
        Log.d(TAG, "Tracking : $visibleRows")
        visibleRows?.let {
            val from = it.firstCompletelyVisible
            val to = it.lastCompletelyVisible
            for (i in from..to) {
                val item = listData[i]
                //You can send the list item to network or store in local DB
                item.isViewed = true
                listData.set(i, item)
            }
            itemAdapter.updateListItems(listData)
            //itemAdapter.notifyItemRangeChanged(from, to - from)
        }
    }

    private fun createDummyList() {
        listData = arrayListOf()
        for (i in 0..1000) {
            val item = ListItem(i, false)
            listData.add(item)
        }
    }

}