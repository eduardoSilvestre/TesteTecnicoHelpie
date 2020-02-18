package com.eduardo.desafiotecnico.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.eduardo.desafiotecnico.PhotosActivity
import com.eduardo.desafiotecnico.R
import com.eduardo.desafiotecnico.model.User
import kotlinx.android.synthetic.main.user_cell.view.*

class UserAdapter(var users: List<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(view: ViewGroup, viewType: Int): UserViewHolder {
        val layout = LayoutInflater.from(view.context).inflate(R.layout.user_cell, view, false)
        return UserViewHolder(layout)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val id: TextView = itemView.userIdTextView
        val name: TextView = itemView.userNameTextView
        val email: TextView = itemView.userEmailTextView
        val card: CardView = itemView.cardView

        fun bind(user: User) {
            id.text = user.id.toString()
            name.text = user.name
            email.text = user.email
            card.setOnClickListener {
                val intent = Intent(itemView.context, PhotosActivity::class.java)
                val bundle = Bundle()
                bundle.putString("USERKEY", user.name)
                intent.putExtra("BUNDLETESTE", bundle)
                itemView.context.startActivity(intent)
            }

        }
    }
}