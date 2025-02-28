package com.example.agenda_app.ui.deletecontacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.agenda_app.database.ContactsDbHelper
import com.example.agenda_app.model.Contact

class DeleteContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val dbHelper = ContactsDbHelper(application.applicationContext)
    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = _contacts

    private val _deleteResult = MutableLiveData<Event<String>>()
    val deleteResult: LiveData<Event<String>> = _deleteResult

    init {
        loadContacts()
    }

    fun loadContacts() {
        val contactsList = dbHelper.getAllContacts()
        _contacts.postValue(contactsList)
    }

    fun deleteContact(contact: Contact) {
        val result = dbHelper.deleteContact(contact.id)

        if (result > 0) {
            _deleteResult.value = Event("Contacto eliminado correctamente")
            // Recargar la lista de contactos
            loadContacts()
        } else {
            _deleteResult.value = Event("Error al eliminar el contacto")
        }
    }
}

class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}

