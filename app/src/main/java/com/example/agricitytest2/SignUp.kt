package com.example.agricitytest2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView
import android.os.Bundle
import android.os.Handler
import com.example.agricitytest2.R
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.Button
import android.widget.Toast
import com.example.agricitytest2.databinding.ActivitySignupBinding
import com.vishnusivadas.advanced_httpurlconnection.PutData

class SignUp : AppCompatActivity() {
/*    var textInputEditFullname: TextInputEditText? = null
    var textInputEditUsername: TextInputEditText? = null
    var textInputEditPassword: TextInputEditText? = null
    var textInputEditEmail: TextInputEditText? = null
    var buttonSignUp: Button? = null
    var textViewLogin: TextView? = null*/

    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textInputEditFullname = binding.fullname
        val textInputEditUsername = binding.username
        val textInputEditPassword = binding.password
        val textInputEditEmail = binding.email
        val buttonSignUp = binding.buttonSignUp
        val textViewLogin = binding.loginText
        val progressBar = binding.progress

        textViewLogin.setOnClickListener {
            val changeToLogin = Intent(applicationContext, Login::class.java)
            startActivity(changeToLogin)
            finish()
        }



        buttonSignUp.setOnClickListener {
            val fullname = textInputEditFullname.text.toString()
            val username = textInputEditUsername.text.toString()
            val password = textInputEditPassword.text.toString()
            val email = textInputEditEmail.text.toString()

            if (!(fullname.isNullOrEmpty() || username.isNullOrEmpty() || password.isNullOrEmpty() || email.isNullOrEmpty())) {

                progressBar.visibility = View.VISIBLE
                //Start ProgressBar first (Set visibility VISIBLE)
                val handler = Handler(Looper.getMainLooper())
                handler.post {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    val field = arrayOfNulls<String>(4)
                    field[0] = "fullname"
                    field[1] = "username"
                    field[2] = "password"
                    field[3] = "email"
                    //Creating array for data
                    val data = arrayOfNulls<String>(4)
                    data[0] = fullname
                    data[1] = username
                    data[2] = password
                    data[3] = email
                    val putData = PutData(
                        "http://192.168.1.126/LogIn-SignUp-master/signup.php",
                        "POST",
                        field,
                        data
                    )
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            progressBar.visibility = GONE
                            val result = putData.result
                            //End ProgressBar (Set visibility to GONE)
                            if (result.equals("Sign Up Success")) {
                                Toast.makeText(applicationContext, R.string.registerSuccess, Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(applicationContext, Login::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                            }
                            Log.i("PutData", result)
                        }
                    }
                    //End Write and Read data with URL
                }
            } else {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()

            }
        }
    }
}