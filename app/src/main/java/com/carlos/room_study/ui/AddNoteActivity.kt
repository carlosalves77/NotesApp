package com.carlos.room_study.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.carlos.room_study.databinding.ActivityAddBinding
import com.carlos.room_study.model.Note
import com.carlos.room_study.viewmodel.NoteViewModel

class AddNoteActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityAddBinding
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mNoteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        binding.saveBtn.setOnClickListener(this)
        binding.backBtn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.saveBtn.id -> {
                addNote()
            }
        }
        when (v?.id) {
            binding.backBtn.id -> {
                finish()
            }
        }
    }

    private fun addNote() {

        val firstName = binding.etNoteTitle.text.toString()
        val lastName = binding.etNoteDesc.text.toString()


        if (inputCheck(firstName, lastName)) {
            // Create User Object
            val note = Note(0, firstName, lastName)
//            // Add Data to Database
            mNoteViewModel.addNote(note)
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