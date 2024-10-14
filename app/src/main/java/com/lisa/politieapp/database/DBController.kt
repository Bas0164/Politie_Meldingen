package com.lisa.politieapp.database

import android.content.Context
import android.os.StrictMode
import android.widget.Toast
import com.lisa.politieapp.classes.User
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

class DBController (var context: Context){

        private var dbusername = "politie_de_bergse_krabben"
        private var dbpassword = "8Y?8ob1h7"
        private var host = "adainforma.tk:3306"
        private var dbname = "bp3_politie_de_bergse_krabben"
        private var conn : Connection? = null

    //Functie voor het opstellen van een connectie met de database
    fun dbCon() : Connection? {

            try {
                Class.forName("com.mysql.jdbc.Driver")
                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)
                conn = DriverManager.getConnection(
                    "jdbc:mysql://${host}/${dbname}",
                    "${dbusername}",
                    "${dbpassword}"
                )
                println("Connection is made!!")

            } catch (e:Exception) {
                e.printStackTrace()
            }
            return conn

        }

        fun insertUser(user : User, connection: Connection) : Boolean {

            var isUserInserted = false

            try {
                //Op de connectie met de database een createStatement aanmaken
                val stmt = connection.createStatement()

                //De parameters van user toekennen aan de values in de query
                val insertQuery = "INSERT INTO user VALUES(0, '${user.username}', '${user.password}', '${user.city}', 0)"

                //Het uitvoeren van de query
                val insertedRows = stmt?.executeUpdate(insertQuery)

                //Checken of dat de user is toegevoegd
                if(insertedRows != null) {
                    isUserInserted = true
                }

            } catch (e:Exception) {
                e.printStackTrace()
            }

            //de toegevoegde user teruggeven
            return isUserInserted
        }

        fun selectUser(inptusername:String, inptpassword:String, connection:Connection) : Boolean {

            var isUserInserted = false

            try {
                //Op de connectie met de database een statement aanmaken
                val stmt = connection.createStatement()
                //De query voor de database opstellen
                val selectQuery = "SELECT * FROM user WHERE username = '$inptusername' AND password = '$inptpassword'"
                //De bovenstaande query uitvoeren
                val resultSet = stmt?.executeQuery(selectQuery)


                //Door alle rows in de database loopen tot de gebruiker overeenkomt
                while(resultSet!!.next()) {
                    val username = resultSet.getString("username")
                    val password = resultSet.getString("password")
                    if(username == inptusername && password == inptpassword) {
                        val (a, b) = Pair(username, password)
                    } else {
                        Toast.makeText(context, "Deze gebruiker bestaat niet...", Toast.LENGTH_LONG).show()
                    }
                    isUserInserted = true
                }

            }catch (e:Exception) {
                e.printStackTrace()
            }
            return isUserInserted

        }
    }
