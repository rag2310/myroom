package com.rago.myroom.data.repository

import com.rago.myroom.data.dao.NoteDAO
import com.rago.myroom.data.model.NoteModel
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDAO: NoteDAO) {

    suspend fun insertNotes(noteModel: NoteModel) =
        noteDAO.insertNote(noteModel)

    suspend fun updateNotes(noteModel: NoteModel) =
        noteDAO.updateNote(noteModel)

    suspend fun deleteNotes(noteModel: NoteModel) =
        noteDAO.deleteNote(noteModel)

    fun getAllNotes(): Flow<List<NoteModel>> = noteDAO.getAllNotes()
}