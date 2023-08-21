package com.carlos.room_study.adapter

import android.annotation.SuppressLint
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlos.room_study.databinding.NotesRowBinding
import com.carlos.room_study.model.User
import com.carlos.room_study.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class UserAdapter() : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var user = emptyList<User>()

    inner class UserViewHolder(val binding: NotesRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            NotesRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount() = user.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = user[position]
        holder.binding.etFirstName.text = currentUser.firstName
        holder.binding.etLastName.text = currentUser.lastName
        holder.binding.notesRow.setOnLongClickListener {
            onLongClick(holder, currentUser)
            true
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<User>) {
        this.user = newData
        notifyDataSetChanged()

    }

    private fun onLongClick( holder: UserViewHolder, currentUser: User) {
        holder.binding.btnDelete.visibility = View.VISIBLE
        holder.binding.etFirstName.visibility = View.GONE
        holder.binding.etLastName.visibility = View.GONE
        holder.binding.notesRow.backgroundTintList
        holder.binding.notesRow.setOnClickListener {
            Snackbar.make(
                it,
                "Are you sure you want to delete ${currentUser.firstName}?",
                Snackbar.LENGTH_INDEFINITE
            )
                .setAction("Yes") {
                    // Delete User
                    UserViewModel(Application()).deleteUser(currentUser)
                    holder.binding.btnDelete.visibility = View.GONE
                    holder.binding.etFirstName.visibility = View.VISIBLE
                    holder.binding.etLastName.visibility = View.VISIBLE
                }.show()

        }
    }


}