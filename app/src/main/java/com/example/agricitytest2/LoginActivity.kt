package com.example.agricitytest2;

import androidx.appcompat.app.AppCompatActivity
import com.example.agricitytest2.databinding.LoginBinding
import android.os.Bundle;

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}