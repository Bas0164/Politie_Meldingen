package com.lisa.politieapp.api

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lisa.politieapp.R
import com.lisa.politieapp.classes.Report

// De ReportAdapter klasse erft van ArrayAdapter en is bedoeld om de lijst van Report objecten weer te geven
class ReportAdapter(context: Context, reports: ArrayList<Report>) : ArrayAdapter<Report>(context, 0, reports) {

    // Deze functie wordt aangeroepen om een weergave van een item in de lijst te maken
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Als er al een weergave is, gebruik die, anders maak een nieuwe
        var view = convertView
        if (view == null) {
            // Maak een nieuwe weergave aan door het layout bestand te inflateren
            view = LayoutInflater.from(context).inflate(R.layout.list_item_report, parent, false)
        }

        // Haal het Report object op voor de huidige positie in de lijst
        val report = getItem(position) as Report

        // Vind de TextView voor de datum in de weergave en stel de tekst in op de publicatiedatum van het Report object
        val textViewDate = view!!.findViewById<TextView>(R.id.listItemtxtReportDate)
        textViewDate.text = report.publicatiedatum

        // Vind de TextView voor de titel in de weergave en stel de tekst in op de titel van het Report object
        val textViewTitle = view!!.findViewById<TextView>(R.id.listItemtxtReportTitle)
        textViewTitle.text = report.titel

        // Vind de ImageView voor de afbeelding in de weergave
        val imageView = view!!.findViewById<ImageView>(R.id.listItemimgReportImage)
        // Haal de URL van de afbeelding op uit het Report object
        val imageUrl = report.afbeeldingurl
        // Controleer of de imageUrl null is of niet. Als het null is, laad dan de standaardafbeelding.
        if (imageUrl != null && imageUrl.isNotEmpty()) {
            Glide.with(context).load(imageUrl).into(imageView)
        } else {
            Glide.with(context).load(R.drawable.nopictureimage).into(imageView)
        }

        // Geef de weergave terug om in de lijst te worden geplaatst
        return view
    }
}



