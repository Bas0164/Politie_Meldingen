package com.lisa.politieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentManager
import com.lisa.politieapp.fragments.RegisterFragment
import com.lisa.politieapp.database.DBController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var dbController = DBController(this.applicationContext)
        var conn = dbController.dbCon()
        val inptUsername : EditText = findViewById(R.id.inptUsername)
        val inptPassword : EditText = findViewById(R.id.inptPassword)


        //Navigeren naar de register fragment
        val btnLogin : Button = findViewById(R.id.btnLogin)
        val txtMakeAcount : TextView = findViewById(R.id.txtMakeAccount)

        btnLogin.setOnClickListener(View.OnClickListener {
            val username = inptUsername.text.toString()
            val password = inptPassword.text.toString()
            //De databas functie uitvoeren en de userinput hier aan meegeven
            val isUserInserted = conn?.let {it1 -> dbController.selectUser(username, password, it1)}
            //Is de user aanwezig dan wordt er ingelogd
            if(isUserInserted == true) {
                Toast.makeText(this, "Welkom " + username + "!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Oeps, er is iets mis gegaan", Toast.LENGTH_LONG).show()
            }
        })

        txtMakeAcount.setOnClickListener( View.OnClickListener {
            GotToRegister()
        })
    }

    //Functie voor het navigeren naar de register fragment
    private fun GotToRegister() {
        var registerFragment = RegisterFragment()
        var fm : FragmentManager = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(android.R.id.content, registerFragment)
        //addToBackStack(null)
        ft.commit()
    }
}