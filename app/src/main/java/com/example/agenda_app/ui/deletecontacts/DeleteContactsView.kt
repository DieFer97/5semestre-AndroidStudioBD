package com.example.agenda_app.ui.deletecontacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeleteContactsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aquí podrás eliminar contactos"
    }
    val text: LiveData<String> = _text
}