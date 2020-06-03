package com.mobileappdevelopment.todofirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ShowTodoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_todo)

        val todoLog = findViewById<TextView>(R.id.todos)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "API Demo"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.reference

        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                val value = dataSnapshot.toString()
                todoLog.append("\n$value")
            }

            override fun onCancelled(p0: DatabaseError) {
                // Failed to read value
                Log.w("API Demo", "Failed to read value.", p0.toException())
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
