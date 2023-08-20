package com.carlos.room_study.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlos.room_study.databinding.UserRowBinding
import com.carlos.room_study.model.User
import com.squareup.picasso.Picasso

class UserAdapter(): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var user = emptyList<User>()

    inner class UserViewHolder(val binding: UserRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(UserRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = user.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = user[position]
        holder.binding.etFirstName.text = currentUser.firstName
        holder.binding.etLastName.text = currentUser.lastName
        holder.binding.etAge.text = currentUser.age.toString()
        Picasso.get().load("https://cdn-icons-png.flaticon.com/512/149/149071.png").into(holder.binding.imageUser)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: List<User>) {
        this.user = newData
        notifyDataSetChanged()
    }
}