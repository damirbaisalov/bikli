package kz.bfgroup.bikli.main_window.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.databinding.ActivityMainBinding

class MainWindow : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
//    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initViews()

    }
//
//    private fun initViews() {
//        bottomNavigationView = findViewById(R.id.main_window_bottom_navigation)
//    }

//    private fun loadFragments(fragment: Fragment) {
//
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.container, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }


}