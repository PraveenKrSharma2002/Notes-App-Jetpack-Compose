package com.example.notesapp.data.repository

import com.example.notesapp.data.dao.NoteDao
import com.example.notesapp.data.entity.NoteEntity

class NoteRepository(private val noteDao: NoteDao) {

    // READ
    val allNotes = noteDao.getAllNotes()

    // CREATE
    suspend fun insert(note: NoteEntity) {
        noteDao.insertNote(note)
    }

    suspend fun update(note: NoteEntity) {
        noteDao.updateNote(note)
    }

    // DELETE
    suspend fun delete(note: NoteEntity) {
        noteDao.deleteNote(note)
    }
}