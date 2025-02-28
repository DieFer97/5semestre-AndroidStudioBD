package com.example.agenda_app.ui.viewcontacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewContactsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Aquí se mostrarán tus contactos"
    }
    val text: LiveData<String> = _text
}