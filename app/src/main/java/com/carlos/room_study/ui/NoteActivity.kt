package com.carlos.room_study.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.carlos.room_study.R
import com.carlos.room_study.databinding.ActivityNoteBinding
import com.carlos.room_study.model.User
import com.carlos.room_study.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar

class NoteActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityNoteBinding
    private lateinit var mUserViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        binding.backBtn.setOnClickListener(this)
        binding.deleteBtn.setOnClickListener(this)
        binding.editBtn.setOnClickListener(this)
        binding.saveBtn.setOnClickListener(this)

        getNote()

    }


    override fun onClick(v: View?) {
       when (v?.id) {
           binding.backBtn.id -> {
             return finish()
           }
       }
        when (v?.id) {
            binding.deleteBtn.id -> {
                // delete a user by id
                val id = intent.getIntExtra("id", 0)
                mUserViewModel.deleteUser(id)
                return finish()
            }
        }
        when(v?.id) {
            binding.editBtn.id -> {

                binding.etNoteTitle.focusable = View.FOCUSABLE
                binding.etNoteTitle.isFocusableInTouchMode = true

                binding.etNote.focusable = View.FOCUSABLE
                binding.etNote.isFocusableInTouchMode = true
                binding.saveBtn.visibility = View.VISIBLE
                binding.editBtn.visibility = View.GONE
            }
        }
        when (v?.id) {
            binding.saveBtn.id -> {
                val id = intent.getIntExtra("id", 0)
                val firstName = binding.etNoteTitle.text.toString()
                val lastName = binding.etNote.text.toString()
                val user = User(id, firstName, lastName)
                mUserViewModel.updateUser(user)


                binding.etNoteTitle.focusable = View.NOT_FOCUSABLE
                binding.etNoteTitle.isFocusableInTouchMode = false

                binding.etNote.focusable = View.NOT_FOCUSABLE
                binding.etNote.isFocusableInTouchMode = false

                binding.saveBtn.visibility = View.GONE
                binding.editBtn.visibility = View.VISIBLE

                val snackBar = Snackbar.make(v, "Note Saved", Snackbar.LENGTH_SHORT)
                val view = snackBar.view
                view.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
                view.textDirection = View.TEXT_ALIGNMENT_CENTER
                snackBar.show()
            }
        }
    }
    private fun getNote() {
        binding.etNoteTitle.focusable = View.NOT_FOCUSABLE
        binding.etNoteTitle.isFocusableInTouchMode = false

        binding.etNote.focusable = View.NOT_FOCUSABLE
        binding.etNote.isFocusableInTouchMode = false

        val title = intent.getStringExtra("firstName")
        val note = intent.getStringExtra("lastName")

        binding.etNoteTitle.setText(title)
        binding.etNote.setText(note)
    }

}