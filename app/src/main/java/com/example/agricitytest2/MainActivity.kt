package com.example.agricitytest2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agricitytest2.databinding.DashboardBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: DashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = DashboardBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // register all the ImageButtons with their appropriate ID
            val backB = binding.backB
            val logOutB = binding.logOutB
            val profileB = binding.profileB

            // register all the Buttons with their appropriate IDs
            val todoB = binding.todoB
            val editProfileB = binding.editProfileB

            // register all the card views with their appropriate IDs
            val contributeCard = binding.contributeCard
            val practiceCard = binding.practiceCard
            val learnCard = binding.learnCard
            val interestsCard = binding.interestsCard
            val helpCard = binding.helpCard
            val settingsCard = binding.settingsCard


            // handle each of the image buttons with the OnClickListener
            backB.setOnClickListener {
                Toast.makeText(this, "Back Button", Toast.LENGTH_SHORT).show()
            }
            logOutB.setOnClickListener {
                Toast.makeText(this, "Logout Button", Toast.LENGTH_SHORT).show()
            }
            profileB.setOnClickListener {
                Toast.makeText(this, "Profile Image", Toast.LENGTH_SHORT).show()
            }

            // handle each of the buttons with the OnClickListener
            todoB.setOnClickListener {
                Toast.makeText(this, "TODO allow", Toast.LENGTH_SHORT).show()
            }
            editProfileB.setOnClickListener {
                Toast.makeText(this, "Editing Profile", Toast.LENGTH_SHORT).show()
            }

            // handle each of the cards with the OnClickListener
            contributeCard.setOnClickListener {
                Toast.makeText(this, "Contribuir", Toast.LENGTH_SHORT).show()
            }
            practiceCard.setOnClickListener {
                Toast.makeText(this, "Practice Programming", Toast.LENGTH_SHORT).show()
            }
            learnCard.setOnClickListener {
                Toast.makeText(this, "Learn Programming", Toast.LENGTH_SHORT).show()
            }
            interestsCard.setOnClickListener {
                Toast.makeText(this, "Filter your Interests", Toast.LENGTH_SHORT).show()
            }
            helpCard.setOnClickListener {
                Toast.makeText(this, "Anything Help you want?", Toast.LENGTH_SHORT).show()
            }
            settingsCard.setOnClickListener {
                Toast.makeText(this, "Change the settings", Toast.LENGTH_SHORT).show()
            }
    }

    public override fun onStart() = super.onStart()
    public override fun onResume() = super.onResume()
    public override fun onPause() = super.onPause()
    public override fun onDestroy() = super.onDestroy()
    public override fun onRestart() = super.onRestart()
}
