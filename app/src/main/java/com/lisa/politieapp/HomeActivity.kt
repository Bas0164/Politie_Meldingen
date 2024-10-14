package com.lisa.politieapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.lisa.politieapp.api.APIController
import com.lisa.politieapp.database.DBController
import com.lisa.politieapp.fragments.OverviewFragment
import com.lisa.politieapp.fragments.ProfileFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var dbController = DBController(this)
        dbController.dbCon()

        // Maak een instantie van APIController met de huidige activiteit als context
        var apiController = APIController(this)
        // Vind de ListView in de layout
        var listViewReports : ListView = findViewById(R.id.homeListReports)
        // Haal alle auto's op met behulp van APIController
        apiController.listReports(listViewReports)

        //navigeren naar de overview fragment
        val buttonMoreReports = findViewById<Button>(R.id.homeButtonMoreReports)
        buttonMoreReports.setOnClickListener() {
            GoToOverview()
        }

        //navigeren naar de profile fragment
        val buttonProfile = findViewById<ImageButton>(R.id.homeBtnProfile)
        buttonProfile.setOnClickListener() {
            GoToProfile()
        }

    }
        //Functie voor het navigeren naar de overview fragment
        private fun GoToOverview() {
            var overviewFragment = OverviewFragment()
            var fm : FragmentManager = supportFragmentManager
            var ft = fm.beginTransaction()
            ft.replace(R.id.main, overviewFragment)
            ft.commit()
        }

        //Functie voor het navigeren naar de profile fragment
        private fun GoToProfile() {
            var profileFragment = ProfileFragment()
            var fm : FragmentManager = supportFragmentManager
            var ft = fm.beginTransaction()
            ft.replace(R.id.main, profileFragment)
            ft.commit()
        }
}