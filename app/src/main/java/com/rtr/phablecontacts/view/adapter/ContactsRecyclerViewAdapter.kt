package com.rtr.phablecontacts.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rtr.phablecontacts.databinding.LayoutContactBinding
import com.rtr.phablecontacts.model.ContactItem
import com.rtr.phablecontacts.utils.OnClickContactItem

/**
 * Created by RAHUL T R
 * Copyright (c) 2020 . All rights reserved.
 */

/**
 * Recycler view adapter class for listing contacts
 */
class ContactsRecyclerViewAdapter(var contactList : MutableList<ContactItem>, var listener : OnClickContactItem) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContactViewHolder(LayoutContactBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
    }

    override fun getItemCount(): Int = contactList.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ContactViewHolder).setModel(contactList[position])
    }

    /**
     * Method to update the contact list
     */
    fun updateContactList(contactList : MutableList<ContactItem>){
        this.contactList = contactList
        notifyDataSetChanged()
    }

    /**
     * View holder class for contact listing
     */
    inner class ContactViewHolder(var binding: LayoutContactBinding,  var listener : OnClickContactItem) :
        RecyclerView.ViewHolder(binding.root) {
        fun setModel(item: ContactItem) {
            item.listener = listener
            binding.model = item
        }
    }
}