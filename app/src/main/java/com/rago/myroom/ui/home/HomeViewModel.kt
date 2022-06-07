package com.rago.myroom.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.rago.myroom.data.model.NoteModel
import com.rago.myroom.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.*

class HomeViewModel(private val repository: NoteRepository) : ViewModel() {

    private val _notes: MutableStateFlow<List<NoteModel>> = MutableStateFlow(mutableListOf())
    val notes: StateFlow<List<NoteModel>>
        get() = _notes

    fun getAllNotes() {
        viewModelScope.launch {
            repository.getAllNotes().collect {
                if (it.isNotEmpty()) {
                    _notes.value = it
                }
            }
        }
    }

    fun addNotes(){
        viewModelScope.launch {
            repository.insertNotes(NoteModel(noteId = Date().time, title = "Hola ${Date().time}"))
        }
    }
}

@Suppress("UNCHECKED_CAST")
class HomeViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("")
    }

}