package com.carlos.room_study.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlos.room_study.databinding.NotesRowBinding
import com.carlos.room_study.model.Note
import com.carlos.room_study.ui.NoteActivity
import kotlin.random.Random

class UserAdapter() : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var note = emptyList<Note>()

     class UserViewHolder(val binding: NotesRowBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            NotesRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount() = note.size


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = note[position]
        holder.binding.etNoteTitle.text = currentUser.noteTitle

        holder.binding.notesRow.setOnClickListener {
            onNoteClicked(currentUser, holder)
        }
        holder.binding.etNoteTitle.setOnClickListener {
            onNoteClicked(currentUser, holder)
        }

        changeColorOnlyOnce(holder)


    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<Note>) {
        this.note = newData
        notifyDataSetChanged()

    }

    private fun onNoteClicked(currentNote: Note, holder: UserViewHolder) {
        val intent = Intent(holder.itemView.context, NoteActivity::class.java)
        intent.putExtra("id", currentNote.id)
        intent.putExtra("noteTitle", currentNote.noteTitle)
        intent.putExtra("note", currentNote.note)
        holder.itemView.context.startActivity(intent)
    }

    private fun changeColorOnlyOnce(holder: UserViewHolder) {
        val rnd = Random
        val color: Int =
            android.graphics.Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.binding.notesRow.backgroundTintList =
            android.content.res.ColorStateList.valueOf(color)
    }


}