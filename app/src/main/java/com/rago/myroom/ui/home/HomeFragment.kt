package com.rago.myroom.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rago.myroom.R
import com.rago.myroom.data.db.MyRoomDatabase
import com.rago.myroom.data.model.NoteModel
import com.rago.myroom.data.repository.NoteRepository
import com.rago.myroom.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private val mViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(NoteRepository(MyRoomDatabase.getDatabase(requireContext()).noteDAO()))
    }
    private lateinit var adapter: NotesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        mBinding.rvNotes.layoutManager = LinearLayoutManager(requireActivity())
        adapter = NotesListAdapter {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailsNoteFragment(
                    it
                )
            )
        }
        mBinding.rvNotes.adapter = adapter
        mBinding.addNote.setOnClickListener {
            mViewModel.addNotes()
        }
        createFlow()
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getAllNotes()
    }

    private fun createFlow() {
        lifecycleScope.launchWhenCreated {
            launch {
                mViewModel.notes.collect(::setNotes)
            }
        }
    }

    private fun setNotes(notes: List<NoteModel>) {
        adapter.submitList(notes)
    }
}