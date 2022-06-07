package com.rago.myroom.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rago.myroom.data.model.NoteModel
import com.rago.myroom.databinding.RowNoteBinding

class NotesListAdapter(
    private val onNavDetails: (NoteModel) -> Unit
) : ListAdapter<NoteModel,
        NotesListAdapter.NotesViewHolder>(NotesComparator()) {

    class NotesViewHolder(
        private val binding: RowNoteBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel, onClick: (NoteModel) -> Unit) {
            binding.apply {
                idNote.text = item.noteId?.toString() ?: "0"
                content.setOnClickListener {
                    onClick(item)
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): NotesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: RowNoteBinding = RowNoteBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
                return NotesViewHolder(binding)
            }
        }
    }

    class NotesComparator : DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean =
            oldItem.noteId == newItem.noteId

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean =
            oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder =
        NotesViewHolder.create(parent)

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(getItem(position), onNavDetails)
    }
}