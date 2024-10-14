package com.lisa.politieapp.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.lisa.politieapp.HomeActivity
import com.lisa.politieapp.MainActivity
import com.lisa.politieapp.R
import com.lisa.politieapp.classes.User
import com.lisa.politieapp.database.DBController

class RegisterFragment : Fragment() {
        lateinit var fragView : View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragView = inflater.inflate(R.layout.fragment_register, container, false)
        var dbController = DBController(requireContext())
        var conn = dbController.dbCon()

        //Alle elementen op de pagina initialiseren
        val btnBack : Button = fragView.findViewById(R.id.btnBack)
        val btnRegister : Button = fragView.findViewById(R.id.btnRegister)

        //teruggaan naar loginpagina
        btnBack.setOnClickListener(View.OnClickListener {
            navigateToLogin()
        })

        btnRegister.setOnClickListener(View.OnClickListener {
            //De user in een variabele stoppen
            var user = userInput()
            //De database functie uitvoeren en de user hieraan meegeven
            val isUserInserted = conn?.let { it1 -> dbController.insertUser(user, it1) }
            //is de user succesvol toegevoegd dan ga je terug naar login
            if(isUserInserted == true) {
                Toast.makeText(context, "Registratie is succesvol!", Toast.LENGTH_LONG).show()
                navigateToLogin()
            } else {
                Toast.makeText(context, "Oeps, er is iets mis gegaan...", Toast.LENGTH_LONG).show()
            }
        })
        return fragView
    }

    //Deze functie handelt de navigatie naar het login scherm
    fun navigateToLogin() {
        var intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)
    }

    //Deze functie koppelt de input van de gebruiker aan de parameters van het User object
    fun userInput() : User{
        val txtUsername : EditText? = fragView.findViewById(R.id.txtUsername)
        val txtPassword : EditText? = fragView.findViewById(R.id.txtPassword)
        val txtCity : EditText? = fragView.findViewById(R.id.txtCity)
        val username = txtUsername?.text.toString()
        val password = txtPassword?.text.toString()
        val city = txtCity?.text.toString()
        return User(0, username = username, password = password, city = city, radius = 0)
    }

}