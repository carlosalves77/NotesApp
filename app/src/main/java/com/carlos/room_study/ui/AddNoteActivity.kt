package com.carlos.room_study.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.carlos.room_study.databinding.ActivityAddBinding
import com.carlos.room_study.model.User
import com.carlos.room_study.viewmodel.UserViewModel

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        binding.confirmBtn.setOnClickListener {
            addUser()

        }

    }

    private fun addUser() {

        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()


        if (inputCheck(firstName, lastName)) {
            // Create User Object
            val user = User(0, firstName, lastName)
//            // Add Data to Database
            mUserViewModel.addUser(user)
            // Notify User
            Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show()
            // Navigate Back
            finish()
        } else {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName))
    }
}