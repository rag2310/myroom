package com.rago.myroom.data.dao

import androidx.room.*
import com.rago.myroom.data.model.NoteModel
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(noteModel: NoteModel)

    @Update
    suspend fun updateNote(noteModel: NoteModel)

    @Query("SELECT * FROM note_data_table")
    fun getAllNotes(): Flow<List<NoteModel>>

    @Delete
    suspend fun deleteNote(noteModel: NoteModel)
}