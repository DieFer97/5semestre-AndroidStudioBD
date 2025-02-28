package com.example.agenda_app.ui.addcontacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddContactsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aquí podrás agregar nuevos contactos"
    }
    val text: LiveData<String> = _text
}