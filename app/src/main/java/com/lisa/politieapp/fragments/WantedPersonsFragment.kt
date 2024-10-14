package com.lisa.politieapp.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.fragment.app.FragmentManager
import com.lisa.politieapp.HomeActivity
import com.lisa.politieapp.R
import com.lisa.politieapp.api.OverviewAPIController
import com.lisa.politieapp.api.WantedPersonsAPIController

class WantedPersonsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_wanted_persons, container, false)

        //laad de lijst van nieuwsberichten
        val listReports = view.findViewById<ListView>(R.id.wantedListReports)
        val apiController = WantedPersonsAPIController(this)
        apiController.listReports(listReports)

        val backButton = view.findViewById<Button>(R.id.wantedBtnHome)
        // Stel een OnClickListener in voor de terugknop
        backButton.setOnClickListener {
            // Maak een nieuwe intent om de HomeActivity te starten
            val intent = Intent(requireContext(), HomeActivity::class.java)
            // Start de activiteit
            startActivity(intent)
            // Sluit de huidige activiteit
            requireActivity().finish()
        }

        val categoryButton = view.findViewById<ImageButton>(R.id.wantedBtnCategory)
        // Stel een OnClickListener in voor de categorieknop
        categoryButton.setOnClickListener {
            OpenCategory()
        }


        return view
    }

    //Functie voor het navigeren naar de category fragment
    private fun OpenCategory() {
        var categoryFragment = CategoryFragment()
        var fm : FragmentManager = parentFragmentManager
        var ft = fm.beginTransaction()
        ft.add(R.id.fragmentOverviewView, categoryFragment)
        ft.commit()
    }
}