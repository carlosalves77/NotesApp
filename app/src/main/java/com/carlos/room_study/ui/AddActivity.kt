package com.carlos.room_study.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.carlos.room_study.R
import com.carlos.room_study.adapter.UserAdapter
import com.carlos.room_study.databinding.ActivityAddBinding
import com.carlos.room_study.model.User
import com.carlos.room_study.viewmodel.MainViewModel
import com.google.android.material.textfield.TextInputEditText

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mMainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.confirmBtn.setOnClickListener {
            addUser()
        }

    }

    private fun addUser() {

        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text

        if (inputCheck(firstName, lastName, age)) {
            // Create User Object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
//            // Add Data to Database
            mMainViewModel.addUser(user)
            // Notify User
            Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show()
            // Navigate Back
            finish()
        } else {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}