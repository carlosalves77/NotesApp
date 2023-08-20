package com.carlos.room_study.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlos.room_study.adapter.UserAdapter
import com.carlos.room_study.databinding.ActivityMainBinding
import com.carlos.room_study.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UserAdapter()
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mUserViewModel.getAllUser.observe(this) { user ->
            adapter.setData(user)
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

        setRv()
        emptyDatabaseCheck()
    }

    private fun emptyDatabaseCheck() {
        mUserViewModel.getAllUser.observe(this) { data ->
            if (data.isEmpty()) {
                binding.emptyNoteImage.visibility = View.VISIBLE
                binding.emptyNoteText.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.emptyNoteImage.visibility = View.INVISIBLE
                binding.emptyNoteText.visibility = View.INVISIBLE
            }
        }
    }

    private fun setRv() {
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}