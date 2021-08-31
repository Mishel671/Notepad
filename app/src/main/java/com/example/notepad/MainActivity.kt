package com.example.notepad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isInvisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.notepad.databinding.ActivityMainBinding
import com.example.notepad.utilits.APP_ACTIVITY
import com.example.notepad.utilits.AppPreference

class MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar
    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        APP_ACTIVITY = this
        mToolbar = mBinding.toolbar
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(mToolbar)
        title = getString(R.string.title)
        AppPreference.getPreference(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}