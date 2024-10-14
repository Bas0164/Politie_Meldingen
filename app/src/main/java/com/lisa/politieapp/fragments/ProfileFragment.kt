package com.lisa.politieapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import com.lisa.politieapp.HomeActivity
import com.lisa.politieapp.R

class ProfileFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val editButton = view.findViewById<Button>(R.id.profileBtnEdit)
        editButton.setOnClickListener {
            OpenHomeActivity()
        }

        val backButton = view.findViewById<Button>(R.id.profileBtnBack)
        backButton.setOnClickListener {
            OpenHomeActivity()
        }

        val settingsButton = view.findViewById<ImageButton>(R.id.profileBtnSettings)
        settingsButton.setOnClickListener {
            OpenSettings()
        }

        return view
    }

    private fun OpenHomeActivity() {
        // Maak een nieuwe intent om de HomeActivity te starten
        val intent = Intent(requireContext(), HomeActivity::class.java)
        // Start de activiteit
        startActivity(intent)
        // Sluit de huidige activiteit
        requireActivity().finish()
    }

    private fun OpenSettings() {
        var settingsFragment = SettingsFragment()
        var fm : FragmentManager = parentFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.fragmentProfileView, settingsFragment)
        ft.commit()
    }
}