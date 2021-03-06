package com.example.testing.view.mainactivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.testing.R
import com.example.testing.databinding.ActivityMainBinding
import com.example.testing.view.mainactivity.adapters.TabLayoutAdapter
import com.example.testing.viewmodel.MainActivityViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    private val homeActivityViewModel by viewModels<MainActivityViewModel>()

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val adapter = binding?.tl?.tabCount?.let {
            TabLayoutAdapter(
                supportFragmentManager,
                it,
                homeActivityViewModel
            )
        }
        binding?.vp?.adapter = adapter
        binding?.vp?.addOnPageChangeListener(TabLayoutOnPageChangeListener(binding?.tl))
        binding?.tl?.setOnTabSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_activity_actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reload -> {
                Toast.makeText(this, "Si funciona me lo quedo", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        tab?.let { binding?.vp?.currentItem = it.position }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {}
}