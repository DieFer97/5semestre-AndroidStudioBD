package com.example.agenda_app.ui.deletecontacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda_app.R
import com.example.agenda_app.model.Contact

class DeleteContactAdapter(
    private var contacts: List<Contact>,
    private val onDeleteClick: (Contact) -> Unit
) : RecyclerView.Adapter<DeleteContactAdapter.DeleteContactViewHolder>() {

    class DeleteContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.text_delete_contact_name)
        val phoneTextView: TextView = itemView.findViewById(R.id.text_delete_contact_phone)
        val emailTextView: TextView = itemView.findViewById(R.id.text_delete_contact_email)
        val deleteButton: Button = itemView.findViewById(R.id.button_delete_contact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeleteContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_delete_contact, parent, false)
        return DeleteContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeleteContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.nameTextView.text = contact.name
        holder.phoneTextView.text = contact.phone
        holder.emailTextView.text = contact.email

        holder.deleteButton.setOnClickListener {
            onDeleteClick(contact)
        }
    }

    override fun getItemCount() = contacts.size

    fun updateContacts(newContacts: List<Contact>) {
        contacts = newContacts
        notifyDataSetChanged()
    }
}