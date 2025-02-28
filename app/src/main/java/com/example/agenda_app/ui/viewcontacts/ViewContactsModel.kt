package com.example.agenda_app.ui.viewcontacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.agenda_app.database.ContactsDbHelper
import com.example.agenda_app.model.Contact

class ViewContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val dbHelper = ContactsDbHelper(application.applicationContext)
    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = _contacts

    init {
        loadContacts()
    }

    fun loadContacts() {
        val contactsList = dbHelper.getAllContacts()
        _contacts.postValue(contactsList)
    }
}

