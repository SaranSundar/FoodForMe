package com.sszg.foodforme

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity(), AnkoLogger {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance();
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            if (validLoginDetails()) {
                login(username.text.toString(), password.text.toString())
            } else {
                toast("Please Enter A Valid email and password")
            }
        }
    }

    private fun validLoginDetails(): Boolean {
        return true
    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    debug("signInWithEmail:success")
                    val user = mAuth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    debug("signInWithEmail:failure " + task.exception)
                    toast("Authentication failed.")
                }
            }
    }
}
