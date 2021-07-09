package kz.bfgroup.bikli.registration.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import kz.bfgroup.bikli.MainActivity
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.presentation.MainWindow

class Registration: AppCompatActivity() {

    private lateinit var nextButton : Button
    private lateinit var switchCompat: SwitchCompat
    private lateinit var flatNumberTextView: TextView
    private lateinit var flatFloorTextView: TextView
    private lateinit var flatNumberEditText: EditText
    private lateinit var flatFloorEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        bindViews()

        switchCompat.setOnClickListener {
            homeOrFlat()
        }

        nextButton.setOnClickListener {
            startActivity(Intent(this, MainWindow::class.java))
            finish()
        }
    }

    private fun bindViews() {

        switchCompat = findViewById(R.id.activity_registration_switch_button)
        flatNumberTextView = findViewById(R.id.activity_registration_flat_num_text_view)
        flatNumberEditText = findViewById(R.id.activity_registration_flat_num_edit_text)
        flatFloorEditText = findViewById(R.id.activity_registration_flooredit_text)
        flatFloorTextView = findViewById(R.id.activity_registration_floor_text_view)
        nextButton = findViewById(R.id.activity_registration_next_button)
    }

    private fun homeOrFlat() {

        if (switchCompat.isChecked) {
            turnOffVisibility(flatFloorEditText)
            turnOffVisibility(flatFloorTextView)
            turnOffVisibility(flatNumberEditText)
            turnOffVisibility(flatNumberTextView)
        } else {
            turnOnVisibility(flatFloorEditText)
            turnOnVisibility(flatFloorTextView)
            turnOnVisibility(flatNumberEditText)
            turnOnVisibility(flatNumberTextView)
        }
    }

    private fun turnOffVisibility(v : View) {
        v.visibility = View.GONE
        if (v is EditText)
        {
            v.text.clear()
        }
    }

    private fun turnOnVisibility(v : View) {
        v.visibility = View.VISIBLE
    }
}