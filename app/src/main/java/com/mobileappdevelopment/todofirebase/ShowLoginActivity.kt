package com.mobileappdevelopment.todofirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ShowLoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_login)
        //actionbar
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = "Login Demo"
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        // Initialize Firebase Auth
        auth = Firebase.auth
        // Get Text Boxes
        val userName: EditText = findViewById(R.id.user_name)
        val password: EditText = findViewById(R.id.password)
        // Set up Buttons
        val loginButton: Button = findViewById(R.id.log_in)
        val logoutButton: Button = findViewById(R.id.log_out)
        val signupButton: Button = findViewById(R.id.sign_up)
        // Click Listener
        loginButton.setOnClickListener {
            auth.signInWithEmailAndPassword(userName.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }

        logoutButton.setOnClickListener{
            auth.signOut()
            updateUI(null)
        }

        signupButton.setOnClickListener{
            auth.createUserWithEmailAndPassword(userName.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.e("Signup failed", task.exception.toString())
                        Toast.makeText(baseContext, "Signup failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(user: FirebaseUser?) {
        val tv: TextView = findViewById(R.id.current_user) as TextView
        if (user != null) {
            tv.text = user.email
        } else {
            tv.text = "No current user"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
