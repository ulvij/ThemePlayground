package com.example.themeplayground

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat.recreate
import com.example.themeplayground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val PREF_NAME = "THEME"
        const val THEME_ID = "THEME_ID"
        const val THEME_BLACK = "THEME_BLACK"
        const val THEME_GREEN = "THEME_GREEN"
        const val KEY_INPUT = "KEY_INPUT"
    }

    private lateinit var binding: ActivityMainBinding
    private val pref by lazy { getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        handleTheme()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBlack.setOnClickListener {
            pref.edit().putString(THEME_ID, THEME_BLACK).apply()
            recreate()
        }

        binding.buttonGreen.setOnClickListener {
            pref.edit().putString(THEME_ID, THEME_GREEN).apply()
            recreate()
        }
    }

    private fun handleTheme() {
        when (pref.getString(THEME_ID, THEME_GREEN)) {
            THEME_BLACK -> {
                application.setTheme(R.style.AppTheme_BLACK)
                setTheme(R.style.AppTheme_BLACK)
            }
            else -> {
                application.setTheme(R.style.AppTheme_GREEN)
                setTheme(R.style.AppTheme_GREEN)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(KEY_INPUT, binding.textInput.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.textInput.setText(savedInstanceState.getString(KEY_INPUT))
    }

}