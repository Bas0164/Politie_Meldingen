package com.lisa.politieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.lisa.politieapp.R
import com.lisa.politieapp.classes.Report

class OverviewDetailFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_overview_detail, container, false)

        val backButton = view.findViewById<Button>(R.id.overviewDetailBtnBack)
        // Stel een OnClickListener in voor de back knop
        backButton.setOnClickListener {
            OpenOverview()
        }

        val txtInput : EditText = view.findViewById(R.id.overviewDetailTxtInput)
        val lstView : ListView = view.findViewById(R.id.overviewDetailTxtList)
        val messages = ArrayList<String>()
        val lstAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, messages)
        lstView.adapter = lstAdapter
        val saveButton : Button = view.findViewById(R.id.overviewDetailBtnSave)
        saveButton.setOnClickListener(View.OnClickListener {
            val message = txtInput.text.toString()

            if(message == null || message.trim().equals("")) {
                Toast.makeText(requireContext(), "Please enter a message", Toast.LENGTH_SHORT).show()
            }
            if(messages.contains(message)) {
                Toast.makeText(requireContext(), "Bericht bestaat al", Toast.LENGTH_SHORT).show()
            }

            messages.add(message)
            lstAdapter.notifyDataSetChanged()
            txtInput.setText("")
        })

        // Haal het rapport op uit de argumenten bundel
        val report = arguments?.getParcelable("report") as Report?

        // Controleer of het rapport niet null is
        report?.let {
            // Stel de afbeelding in
            val imgWantedPerson = view.findViewById<ImageView>(R.id.overviewDetailImg)
            Glide.with(this).load(it.afbeeldingurl).into(imgWantedPerson)

            // Stel de tekst van de titel in
            val txtTitle = view.findViewById<TextView>(R.id.overviewDetailTxtTitle)
            txtTitle.text = it.titel

            // Stel de tekst van de beschrijving in
            val txtDescription = view.findViewById<TextView>(R.id.overviewDetailTxtDesc)
            txtDescription.text = it.introductie
        }

        return view
    }

    private fun OpenOverview() {
        var overviewDetailFragment = OverviewFragment()
        var fm : FragmentManager = parentFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.fragmentWantedPersonDetailView, overviewDetailFragment)
        ft.commit()
    }
}