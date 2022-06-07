package com.rago.myroom.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "note_data_table")
@Parcelize
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long = 0L,
    @ColumnInfo(name = "note_title")
    var title: String
) : Parcelable
