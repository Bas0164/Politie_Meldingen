package com.lisa.politieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.lisa.politieapp.R
import com.lisa.politieapp.classes.WantedReport

class WantedPersonsDetailFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_wanted_persons_detail, container, false)

        val backButton = view.findViewById<Button>(R.id.wantedDetailBtnBack)
        // Stel een OnClickListener in voor de back knop
        backButton.setOnClickListener {
            OpenWantedPersons()
        }

        // Haal het rapport op uit de argumenten bundel
        val report = arguments?.getParcelable("report") as WantedReport?

        // Controleer of het rapport niet null is
        report?.let {
            // Stel de afbeelding in
            val imgWantedPerson = view.findViewById<ImageView>(R.id.wantedDetailImg)
            Glide.with(this).load(it.afbeeldingurl).into(imgWantedPerson)

            // Stel de tekst van de titel in
            val txtTitle = view.findViewById<TextView>(R.id.wantedDetailTxtTitle)
            txtTitle.text = it.titel

            // Stel de tekst van de beschrijving in
            val txtDescription = view.findViewById<TextView>(R.id.wantedDetailTxtDesc)
            txtDescription.text = it.introductie
        }



        return view
    }

    private fun OpenWantedPersons() {
        var wantedPersonsFragment = WantedPersonsFragment()
        var fm : FragmentManager = parentFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.fragmentWantedPersonDetailView, wantedPersonsFragment)
        ft.commit()
    }
}