package com.rago.myroom.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rago.myroom.data.model.NoteModel
import com.rago.myroom.data.repository.NoteRepository
import com.rago.myroom.ui.home.HomeViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DetailsNoteViewModel(private val repository: NoteRepository) : ViewModel() {

    fun update(noteModel: NoteModel) {
        viewModelScope.launch {
            repository.updateNotes(noteModel)
        }
    }

    fun delete(noteModel: NoteModel) {
        viewModelScope.launch {
            repository.deleteNotes(noteModel)
        }
    }
}


@Suppress("UNCHECKED_CAST")
class DetailsNoteViewModelFactory(private val repository: NoteRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsNoteViewModel::class.java)) {
            return DetailsNoteViewModel(repository) as T
        }
        throw IllegalArgumentException("")
    }

}