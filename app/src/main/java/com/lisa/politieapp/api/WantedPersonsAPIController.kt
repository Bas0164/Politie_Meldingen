package com.lisa.politieapp.api

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.FragmentTransaction
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.lisa.politieapp.R
import com.lisa.politieapp.classes.WantedReport
import com.lisa.politieapp.fragments.WantedPersonsDetailFragment
import com.lisa.politieapp.fragments.WantedPersonsFragment
import org.json.JSONObject

class WantedPersonsAPIController(var fragment: WantedPersonsFragment) {

    fun listReports(listReports: ListView) {
        // URL van de API
        val url = "https://api.politie.nl/v4/gezocht/gezochtepersonen"
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
                var jsonArray = JSONObject(response.toString()).getJSONArray("opsporingsberichten")

                // Converteer de JSONArray naar een lijst van wantedReport objecten
                val wantedReports: ArrayList<WantedReport> = ArrayList()

                for (i in 0 until jsonArray.length()) {
                    // Haal het i-de object uit de jsonArray
                    val wantedReportObject = jsonArray.getJSONObject(i)

                    // Haal het "afbeelding" object uit het wantedReportObject
                    val jsonArrayImages = wantedReportObject.getJSONArray("afbeeldingen")
                    if (jsonArrayImages.length() > 0) {
                        val jsonObject = jsonArrayImages.getJSONObject(0)
                        val imageUrl = jsonObject.getString("url")


                    // Converteer het wantedReportObject naar een wantedReport instantie met Gson
                    val wantedReport = gson.fromJson(wantedReportObject.toString(), WantedReport::class.java)
                    // Stel de afbeeldingurl van het wantedReport object in op de eerder opgehaalde imageUrl
                    wantedReport.afbeeldingurl = imageUrl

                    // Voeg het newsReport object toe aan de wantedReport lijst
                    wantedReports.add(wantedReport)
                }}

                // Maak een wantedReportAdapter voor de ListView
                val adapter = ReportWantedAdapter(fragment.requireContext(), ArrayList(wantedReports))
                // Zet de adapter op de ListView
                listReports.adapter = adapter

                listReports.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, pos, l ->
                    // Haal het geselecteerde rapport op uit de lijst met rapporten
                    var report = wantedReports.get(pos)
                    // Maak een nieuwe instantie van WantedPersonsDetailFragment
                    val detailFragment = WantedPersonsDetailFragment()
                    // Maak een nieuwe bundel om het geselecteerde report door te geven aan het fragment
                    val bundle = Bundle()
                    // Voeg het geselecteerde rapport toe aan de bundel
                    bundle.putParcelable("report", report)
                    // Zet de bundel als argumenten voor het fragment
                    detailFragment.arguments = bundle
                    // Start een nieuwe fragmenttransactie
                    val transaction: FragmentTransaction = fragment.requireFragmentManager().beginTransaction()
                    // Vervang het huidige fragment door het detailFragment
                    transaction.replace(R.id.fragmentWantedPersonView, detailFragment)
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