package kz.bfgroup.bikli.main_window.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.cart_fragment.presentation.CartFragment
import kz.bfgroup.bikli.main_window.history_fragment.presentation.HistoryFragment
import kz.bfgroup.bikli.main_window.home_fragment.presentation.HomeFragment
import kz.bfgroup.bikli.main_window.promotion_fragment.presentation.PromotionFragment
import kz.bfgroup.bikli.main_window.user_fragment.presentation.UserFragment

class MainWindow : AppCompatActivity() {

    private lateinit var nMainFrame: FrameLayout
    private lateinit var bottomNavigationView: BottomNavigationView

    private lateinit var homeFragment: HomeFragment
    private lateinit var promotionFragment: PromotionFragment
    private lateinit var userFragment: UserFragment
    private lateinit var historyFragment: HistoryFragment
    private lateinit var cartFragment: CartFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_window)

        initViews()

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        loadFragments(homeFragment)

    }

    private fun initViews() {
        nMainFrame = findViewById(R.id.main_window_frame_layout)
        bottomNavigationView = findViewById(R.id.main_window_bottom_navigation)

        homeFragment = HomeFragment()
        promotionFragment = PromotionFragment()
        userFragment = UserFragment()
        historyFragment = HistoryFragment()
        cartFragment = CartFragment()
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.item1 ->  {
                loadFragments(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.item2 -> {
                loadFragments(promotionFragment)
                return@OnNavigationItemSelectedListener  true
            }
            R.id.item3 ->{
                loadFragments(userFragment)
                return@OnNavigationItemSelectedListener  true
            }
            R.id.item4 -> {
                loadFragments(historyFragment)
                return@OnNavigationItemSelectedListener  true
            }
            R.id.item5 ->{
                loadFragments(cartFragment)
                return@OnNavigationItemSelectedListener  true
            }
        }
        false
    }

    private fun loadFragments(fragment: Fragment) {

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_window_frame_layout, fragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {

        if (bottomNavigationView.selectedItemId==R.id.item1) {
            super.onBackPressed()
            finish()
        } else {
            bottomNavigationView.selectedItemId = R.id.item1
        }

    }
}