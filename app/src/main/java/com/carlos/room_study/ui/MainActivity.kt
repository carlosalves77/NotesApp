package com.carlos.room_study.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.carlos.room_study.adapter.UserAdapter
import com.carlos.room_study.databinding.ActivityMainBinding
import com.carlos.room_study.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val adapter = UserAdapter()
    private lateinit var mNoteViewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        mNoteViewModel.getAllNote.observe(this) { user ->
            adapter.setData(user)
        }

        binding.fab.setOnClickListener(this)
        binding.searchBtn.setOnClickListener(this)

        binding.searchView.setOnCloseListener {
            onCloseSearchView()
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterSearchView(newText!!)
               return true
            }

        })

        setRv()
        emptyDatabaseCheck()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.fab.id -> {
                startActivity(Intent(this, AddNoteActivity::class.java))
                binding.searchView.visibility = View.GONE
                binding.searchBtn.visibility = View.VISIBLE
                binding.title.visibility = View.VISIBLE
                binding.infoBtn.visibility = View.VISIBLE
            }
        }
        when (v?.id) {
            binding.searchBtn.id -> {
                binding.searchView.visibility = View.VISIBLE
                binding.searchBtn.visibility = View.GONE
                binding.title.visibility = View.GONE
                binding.infoBtn.visibility = View.GONE

                binding.searchView.isIconified = false
                binding.searchView.requestFocus()

            }
        }
    }

    private fun emptyDatabaseCheck() {
        mNoteViewModel.getAllNote.observe(this) { data ->
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

    private fun onCloseSearchView() : Boolean {
        binding.searchView.visibility = View.GONE
        binding.searchBtn.visibility = View.VISIBLE
        binding.title.visibility = View.VISIBLE
        binding.infoBtn.visibility = View.VISIBLE
        return false
    }

    private fun filterSearchView(query: String) {
        val searchQuery = "%$query%"
        mNoteViewModel.searchDatabase(searchQuery).observe(this) { list ->
            list.let {
                adapter.setData(it)
            }
        }
    }

}