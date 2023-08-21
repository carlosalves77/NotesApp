package com.carlos.room_study.adapter

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlos.room_study.databinding.NotesRowBinding
import com.carlos.room_study.model.User
import com.carlos.room_study.ui.NoteActivity
import com.carlos.room_study.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlin.random.Random

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
        holder.binding.notesRow.setOnClickListener() {
            val intent = Intent(holder.itemView.context, NoteActivity::class.java)
            intent.putExtra("id", currentUser.id)
            intent.putExtra("firstName", currentUser.firstName)
            intent.putExtra("lastName", currentUser.lastName)
            holder.itemView.context.startActivity(intent)
        }
//            onLongClick(holder, currentUser)

        val rnd = Random
        val color: Int =
            android.graphics.Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.binding.notesRow.backgroundTintList =
            android.content.res.ColorStateList.valueOf(color)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<User>) {
        this.user = newData
        notifyDataSetChanged()

    }

//    private fun onLongClick(holder: UserViewHolder, currentUser: User) {
//        holder.binding.btnDelete.visibility = View.VISIBLE
//        holder.binding.etFirstName.visibility = View.GONE
//
//        holder.binding.notesRow.backgroundTintList
//        holder.binding.notesRow.setOnClickListener {
//            Snackbar.make(
//                it,
//                "Are you sure you want to delete ${currentUser.firstName}?",
//                Snackbar.LENGTH_INDEFINITE
//            )
//                .setAction("Yes") {
//                    // Delete User
//                    UserViewModel(Application()).deleteUser(currentUser)
//                    holder.binding.btnDelete.visibility = View.GONE
//                    holder.binding.etFirstName.visibility = View.VISIBLE
//                }.show()
//
//        }
//    }


}