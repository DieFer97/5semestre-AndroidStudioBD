package com.example.agenda_app.ui.addcontacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.agenda_app.database.ContactsDbHelper

class AddContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val dbHelper = ContactsDbHelper(application.applicationContext)

    private val _saveStatus = MutableLiveData<SaveResult>()
    val saveStatus: LiveData<SaveResult> = _saveStatus

    fun saveContact(name: String, phone: String, email: String) {
        // Validar que todos los campos estén completos
        when {
            name.isBlank() -> {
                _saveStatus.value = SaveResult(false, "El nombre no puede estar vacío")
                return
            }
            phone.isBlank() -> {
                _saveStatus.value = SaveResult(false, "El teléfono no puede estar vacío")
                return
            }
            email.isBlank() -> {
                _saveStatus.value = SaveResult(false, "El correo electrónico no puede estar vacío")
                return
            }
        }

        // Guardar contacto solo si todos los campos están completos
        val id = dbHelper.addContact(name, phone, email)

        if (id > 0) {
            _saveStatus.value = SaveResult(true, "Contacto guardado correctamente")
        } else {
            _saveStatus.value = SaveResult(false, "Error al guardar el contacto")
        }
    }

    fun resetStatus() {
        _saveStatus.value = null
    }

    data class SaveResult(val success: Boolean, val message: String)
}