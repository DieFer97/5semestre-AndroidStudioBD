package com.example.agenda_app.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ContactsDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Contacts.db"

        // Tabla de contactos
        private const val TABLE_CONTACTS = "contacts"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_PHONE + " TEXT,"
                + COLUMN_EMAIL + " TEXT" + ")")
        db.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }


    // Agregar un nuevo contacto
    fun addContact(name: String, phone: String, email: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        values.put(COLUMN_PHONE, phone)
        values.put(COLUMN_EMAIL, email)

        // Insertar fila
        val id = db.insert(TABLE_CONTACTS, null, values)
        db.close()
        return id
    }

    // Obtener todos los contactos
    fun getAllContacts(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_CONTACTS", null)
    }

    // Eliminar un contacto
    fun deleteContact(id: Int): Int {
        val db = this.writableDatabase
        val result = db.delete(TABLE_CONTACTS, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
        return result
    }
}