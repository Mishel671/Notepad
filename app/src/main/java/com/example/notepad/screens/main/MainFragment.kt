package com.example.notepad.screens.main

import android.os.Bundle
import android.view.*
import android.view.View.inflate
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.notepad.MainActivity
import com.example.notepad.R
import com.example.notepad.databinding.ActivityMainBinding.inflate
import com.example.notepad.databinding.FragmentMainBinding
import com.example.notepad.models.AppNote
import com.example.notepad.utilits.APP_ACTIVITY
import com.example.notepad.utilits.AppPreference

class MainFragment : Fragment() {
    private var _binding:FragmentMainBinding? = null
    private val mBinding get() = _binding!!
    private lateinit var mViewModel:MainFragmentViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter:MainAdapter
    private lateinit var mObserverList:Observer<List<AppNote>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        mAdapter = MainAdapter()
        mRecyclerView = mBinding.recyclerView
        mRecyclerView.adapter = mAdapter
        mObserverList = Observer {
            val list = it.asReversed()
            mAdapter.setList(list)
        }
        mViewModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)
        mViewModel.allNotes.observe(this, mObserverList)
        mBinding.btnAddNote.setOnClickListener{
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNewNoteFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mViewModel.allNotes.removeObserver(mObserverList)
        mRecyclerView.adapter = null
    }

    companion object{
        fun click(note: AppNote){
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noteFragment,bundle)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btn_exit -> {
                mViewModel.signOut()
                AppPreference.setInitUser(false)
                APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_startFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}