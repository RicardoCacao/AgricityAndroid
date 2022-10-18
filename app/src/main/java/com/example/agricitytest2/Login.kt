package com.example.agricitytest2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agricitytest2.databinding.ActivityLoginBinding
import com.vishnusivadas.advanced_httpurlconnection.PutData


class Login() : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textInputEditUsername = binding.username
        val textInputEditPassword = binding.password
        val buttonSignUp = binding.buttonLogin
        val textViewSignUp = binding.signUpText
        val progressBar = binding.progress


        textViewSignUp.setOnClickListener {
            val changeToLogin = Intent(applicationContext, SignUp::class.java)
            startActivity(changeToLogin)
            finish()
        }

        val buttonGoToMain = binding.buttonGoToMain

        buttonGoToMain.setOnClickListener{
            val changeToMain = Intent(applicationContext, MainActivity::class.java)
            startActivity(changeToMain)
            finish()
        }

        buttonSignUp.setOnClickListener {

            val username = textInputEditUsername.text.toString()
            val password = textInputEditPassword.text.toString()
            if (!(username.isNullOrEmpty() || password.isNullOrEmpty())) {
                progressBar.visibility = View.VISIBLE

                val handler = Handler(Looper.getMainLooper())
                handler.post(Runnable {

                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    val field = arrayOfNulls<String>(2)
                    field[0] = "username"
                    field[1] = "password"

                    //Creating array for data
                    val data = arrayOfNulls<String>(2)
                    data[0] = username
                    data[1] = password

                    val putData = PutData(
                        "http://192.168.1.126/LogIn-SignUp-master/login.php",
                        "POST",
                        field,
                        data
                    )
                    if (putData.startPut()) {

                        if (putData.onComplete()) {

                            progressBar.visibility = GONE
                            val result = putData.result

                            if (result.equals("Login Success")) {

                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT)
                                    .show()
                                val intent = Intent(applicationContext, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                                //End ProgressBar (Set visibility to GONE)

                            } else {
                                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                            }
                            Log.i("FetchData", result)
                        }
                        progressBar.visibility = View.INVISIBLE
                    }
                })
            } else {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            }
        }
    }
}