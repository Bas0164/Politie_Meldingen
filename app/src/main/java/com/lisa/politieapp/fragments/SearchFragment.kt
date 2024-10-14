package com.lisa.politieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.lisa.politieapp.R

class SearchFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        // Zoek alle elementen op
        val searchField = view.findViewById<EditText>(R.id.searchTxtSearch)
        val searchButton = view.findViewById<Button>(R.id.searchBtnSearch)
        val reportImage = view.findViewById<ImageView>(R.id.searchImgSearch)
        val reportTitle = view.findViewById<TextView>(R.id.searchTxtSearchTitle)
        val reportDescription = view.findViewById<TextView>(R.id.searchTxtSearchDescription)

        searchButton.setOnClickListener {
            if (searchField.text.toString().isNotEmpty()) {
                val search = searchField.text.toString()

                //check of input gelijk staat aan een van de woorden uit de string.xml van "searchTitle" of "searchDescription" of het woord "wouw"
                if (resources.getString(R.string.searchTitle_text).contains(search, ignoreCase = true) || resources.getString(R.string.searchDescription_text).contains(search, ignoreCase = true) || search == "wouw") {
                    reportImage.setImageResource(R.drawable.wouw)
                    reportTitle.text = resources.getString(R.string.searchTitle_text)
                    reportDescription.text = resources.getString(R.string.searchDescription_text)
                } else {
                    Toast.makeText(context, "Geen resultaten gevonden", Toast.LENGTH_LONG).show()
                }
            }
        }

        val backButton = view.findViewById<Button>(R.id.searchBtnBack)
        backButton.setOnClickListener {
            OpenOverview()
        }

        return view
    }

    private fun OpenOverview() {
        var overviewFragmentManager = OverviewFragment()
        var fm : FragmentManager = parentFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.fragmentSearchView, overviewFragmentManager)
        ft.commit()
    }
}