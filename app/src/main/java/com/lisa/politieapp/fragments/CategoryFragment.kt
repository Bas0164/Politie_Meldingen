package com.lisa.politieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.FragmentManager
import com.lisa.politieapp.R

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class CategoryFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_category, container, false)

        val searchButton = view.findViewById<ImageButton>(R.id.categoryImgSearch)
        searchButton.setOnClickListener {
            OpenSearch()
        }

        val newsButton = view.findViewById<Button>(R.id.categoryBtnNews)
        newsButton.setOnClickListener {
            println("News button clicked")
            OpenNews()
        }

        val wantedPersonsButton = view.findViewById<Button>(R.id.categoryBtnWantedPersons)
        wantedPersonsButton.setOnClickListener {
            println("Wanted persons button clicked")
            OpenWantedPersons()
        }

        val goodNewsButton = view.findViewById<Button>(R.id.categoryBtnGoodNews)
        goodNewsButton.setOnClickListener {
            println("Good news button clicked")
            OpenGoodNews()
        }

        val fragmentView = view.findViewById<View>(R.id.fragmentCategoryView)
        fragmentView.setOnClickListener {
        //sluit fragment wanneer er buiten de fragment wordt geklikt
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
        return view
    }

    //Functie voor het navigeren naar de search fragment
    private fun OpenSearch() {
        var searchFragment = SearchFragment()
        var fm : FragmentManager = parentFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.fragmentOverviewView, searchFragment)
        ft.commit()
    }

    //Functie voor het navigeren naar de category fragment
    private fun OpenNews() {
        var overviewFragment = OverviewFragment()
        var fm : FragmentManager = parentFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.fragmentOverviewView, overviewFragment)
        ft.commit()
    }

    //Functie voor het navigeren naar de wanted persons fragment
    private fun OpenWantedPersons() {
        var wantedPersonsFragment = WantedPersonsFragment()
        var fm : FragmentManager = parentFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.fragmentOverviewView, wantedPersonsFragment)
        ft.commit()
    }

    //Functie voor het navigeren naar de good news fragment
    private fun OpenGoodNews() {
        var goodNewsFragment = GoodNewsFragment()
        var fm : FragmentManager = parentFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.fragmentOverviewView, goodNewsFragment)
        ft.commit()
    }
}