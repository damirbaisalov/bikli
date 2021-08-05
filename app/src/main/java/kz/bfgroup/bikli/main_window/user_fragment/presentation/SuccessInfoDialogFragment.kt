package kz.bfgroup.bikli.main_window.user_fragment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import kz.bfgroup.bikli.R

class SuccessInfoDialogFragment: DialogFragment() {

    private lateinit var rootView: View

    private lateinit var okButton: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.success_info_user_dialog_fragment,container,false)

        okButton = rootView.findViewById(R.id.ok_text_view_button)

        okButton.setOnClickListener{
           dismiss()
            activity?.finish()
        }

        return rootView
    }
}