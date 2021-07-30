package kz.bfgroup.bikli.main_window.user_fragment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kz.bfgroup.bikli.R

class UserFragment: Fragment() {

    private lateinit var viewUser : View

    private lateinit var registrationButton: Button
    private lateinit var authorizationButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewUser = inflater.inflate(R.layout.fragment_user, container, false)

        initViews()

        registrationButton.setOnClickListener {

        }

        authorizationButton.setOnClickListener {

        }

        return viewUser
    }

    private fun initViews() {
        registrationButton = viewUser.findViewById(R.id.fragment_user_reg_button)
        authorizationButton = viewUser.findViewById(R.id.fragment_user_auth_button)
    }
}