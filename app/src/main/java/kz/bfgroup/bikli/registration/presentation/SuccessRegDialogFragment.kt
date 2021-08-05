package kz.bfgroup.bikli.registration.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.main_window.presentation.MainWindow

class SuccessRegDialogFragment: DialogFragment() {

    private lateinit var rootView: View

    private lateinit var successRegButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.success_reg_dialog_fragment,container,false)

        successRegButton = rootView.findViewById(R.id.success_reg_dialog_fragment_button)

        successRegButton.setOnClickListener{
            val intent = Intent(rootView.context, MainWindow::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            activity?.finish()
        }

        return rootView
    }
}