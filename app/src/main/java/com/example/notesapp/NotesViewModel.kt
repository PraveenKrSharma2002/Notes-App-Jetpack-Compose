package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.database.NoteDatabase
import com.example.notesapp.data.entity.NoteEntity
import com.example.notesapp.data.repository.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    // Database → DAO → Repository
    private val noteDao = NoteDatabase
        .getDatabase(application)
        .noteDao()

    private val repository = NoteRepository(noteDao)

    // READ (auto update UI)
    val notes = repository.allNotes.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    // CREATE
    fun addNote(title: String, description: String) {
        viewModelScope.launch {
            repository.insert(
                NoteEntity(
                    title = title,
                    description = description
                )
            )
        }
    }

    fun updateNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.update(note)
        }
    }

    // DELETE
    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }
}