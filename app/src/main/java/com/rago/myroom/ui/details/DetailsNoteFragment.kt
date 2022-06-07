package com.rago.myroom.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rago.myroom.R
import com.rago.myroom.data.db.MyRoomDatabase
import com.rago.myroom.data.model.NoteModel
import com.rago.myroom.data.repository.NoteRepository
import com.rago.myroom.databinding.FragmentDetailsNoteBinding
import java.util.*

class DetailsNoteFragment : Fragment() {

    private val args: DetailsNoteFragmentArgs by navArgs()
    private lateinit var mBinding: FragmentDetailsNoteBinding
    private val mViewModel: DetailsNoteViewModel by viewModels {
        DetailsNoteViewModelFactory(
            NoteRepository(
                MyRoomDatabase.getDatabase(requireContext()).noteDAO()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_details_note, container, false)
        mBinding.noteModel = args.currentNote
        mBinding.button.setOnClickListener {
            val noteModel: NoteModel? = mBinding.noteModel
            noteModel?.let {
                it.title = "Update ${Date().time}"
                mViewModel.update(it)
                mBinding.noteModel = it
            }

        }
        mBinding.delete.setOnClickListener {
            val noteModel: NoteModel? = mBinding.noteModel
            noteModel?.let {
                mViewModel.delete(it)
                findNavController().navigate(DetailsNoteFragmentDirections.actionDetailsNoteFragmentToHomeFragment())
            }
        }
        return mBinding.root
    }
}