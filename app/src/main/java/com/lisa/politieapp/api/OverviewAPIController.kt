package com.lisa.politieapp.api

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.lisa.politieapp.HomeActivity
import com.lisa.politieapp.R
import com.lisa.politieapp.classes.Report
import com.lisa.politieapp.fragments.OverviewDetailFragment
import com.lisa.politieapp.fragments.OverviewFragment
import com.lisa.politieapp.fragments.WantedPersonsDetailFragment
import org.json.JSONObject

class OverviewAPIController(var fragment: OverviewFragment) {

    fun listReports(listReports: ListView) {
        // URL van de API
        val url = "https://api.politie.nl/v4/nieuws"
        // Aanmaken van een Gson object voor het omzetten van JSON naar Kotlin objecten
        val gson = Gson()
        // Aanmaken van een RequestQueue voor het beheren van HTTP-verzoeken
        val queue: RequestQueue = Volley.newRequestQueue(fragment.requireContext())
        // Aanmaken van een StringRequest voor het uitvoeren van een HTTP-verzoek
        val request = StringRequest(
            // Het type verzoek is GET
            Request.Method.GET, url,
            // Deze code wordt uitgevoerd als het verzoek succesvol is
            { response ->
                // Verwerk de ontvangen JSON data
                var jsonArray = JSONObject(response.toString()).getJSONArray("nieuwsberichten")

                // Converteer de JSONArray naar een lijst van Report objecten
                val reports: ArrayList<Report> = ArrayList()

                for (i in 0 until jsonArray.length()) {
                    // Haal het i-de object uit de jsonArray
                    val reportObject = jsonArray.getJSONObject(i)

                    // Haal het "afbeelding" object uit het reportObject
                    val jsonObject = reportObject.getJSONObject("afbeelding")

                    // Haal de "url" string uit het "afbeelding" object
                    val imageUrl = jsonObject.getString("url")

                    // Converteer het reportObject naar een Report instantie met Gson
                    val report = gson.fromJson(reportObject.toString(), Report::class.java)

                    // Stel de afbeeldingurl van het Report object in op de eerder opgehaalde imageUrl
                    report.afbeeldingurl = imageUrl

                    // Voeg het Report object toe aan de reports lijst
                    reports.add(report)
                }

                // Maak een ReportAdapter voor de ListView
                val adapter = ReportAdapter(fragment.requireContext(), ArrayList(reports))
                // Zet de adapter op de ListView
                listReports.adapter = adapter

                listReports.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, pos, l ->
                    // Haal het geselecteerde rapport op uit de lijst met rapporten
                    var report = reports.get(pos)
                    // Maak een nieuwe instantie van WantedPersonsDetailFragment
                    val detailFragment = OverviewDetailFragment()
                    // Maak een nieuwe bundel om het geselecteerde report door te geven aan het fragment
                    val bundle = Bundle()
                    // Voeg het geselecteerde rapport toe aan de bundel
                    bundle.putParcelable("report", report)
                    // Zet de bundel als argumenten voor het fragment
                    detailFragment.arguments = bundle
                    // Start een nieuwe fragmenttransactie
                    val transaction: FragmentTransaction = fragment.requireFragmentManager().beginTransaction()
                    // Vervang het huidige fragment door het detailFragment
                    transaction.replace(R.id.fragmentOverviewView, detailFragment)
                    // Voer de transactie uit
                    transaction.commit()
                }
            },
            // Deze code wordt uitgevoerd als er een fout optreedt bij het verzoek
            { error ->
            })
        // Voeg het verzoek toe aan de RequestQueue
        queue.add(request)
    }
}
