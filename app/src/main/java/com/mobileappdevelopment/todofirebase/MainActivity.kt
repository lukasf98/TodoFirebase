package com.mobileappdevelopment.todofirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Get Buttons
        val loginDemoButton = findViewById<Button>(R.id.login_demo)
        val apiDemoButton = findViewById<Button>(R.id.api_demo)
        // Click Listener
        loginDemoButton.setOnClickListener {
            showLogin()
        }

        apiDemoButton.setOnClickListener{
            showTodos()
        }
    }

    fun showTodos() {
        val intent = Intent(this, ShowTodoActivity::class.java)
        startActivity(intent)
    }

    fun showLogin() {
        val intent = Intent(this, ShowLoginActivity::class.java)
        startActivity(intent)
    }
}
