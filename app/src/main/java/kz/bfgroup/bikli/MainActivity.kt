package kz.bfgroup.bikli

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kz.bfgroup.bikli.registration.presentation.Registration
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            val intent = Intent(this@MainActivity, Registration::class.java)
            startActivity(intent)
            finish()
    }
}