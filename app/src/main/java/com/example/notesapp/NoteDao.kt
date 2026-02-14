package com.example.notesapp.data.dao

import androidx.room.*
import com.example.notesapp.data.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: NoteEntity)

    @Query("SELECT * FROM notes")
    fun getAllNotes(): Flow<List<NoteEntity>>

    @Update
    suspend fun updateNote(note: NoteEntity)


    @Delete
    suspend fun deleteNote(note: NoteEntity)
}