package com.lisa.politieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.lisa.politieapp.R

class SettingsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        val backButton = view.findViewById<Button>(R.id.settingsBtnBack)
        backButton.setOnClickListener {
            OpenSettings()
        }

        return view
    }

    private fun OpenSettings() {
        var profileFragment = ProfileFragment()
        var fm : FragmentManager = parentFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.fragmentSettingsView, profileFragment)
        ft.commit()
    }
}