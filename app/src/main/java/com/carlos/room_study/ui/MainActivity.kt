package com.carlos.room_study.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlos.room_study.adapter.UserAdapter
import com.carlos.room_study.databinding.ActivityMainBinding
import com.carlos.room_study.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UserAdapter()
    private lateinit var mMainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mMainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mMainViewModel.getAllUser.observe(this) { user ->
            adapter.setData(user)
        }


        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        setRv()
    }

    private fun setRv() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}