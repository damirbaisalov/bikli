package kz.bfgroup.bikli.main_window.promotion_fragment.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import kz.bfgroup.bikli.R

class PromotionFragment: Fragment() {

    private lateinit var bikliHelperOn: LinearLayout
    private lateinit var bikliHelperOff: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_promotion, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        bikliHelperOn.visibility = View.GONE
        bikliHelperOff.setOnClickListener {
            checkVisibility()
        }

        bikliHelperOn.setOnClickListener {
            checkVisibility()
        }
    }

    private fun initViews() {
        bikliHelperOn = view?.findViewById(R.id.fragment_promotion_bikli_helper_linear_layout)!!
        bikliHelperOff = view?.findViewById(R.id.fragment_promotion_hand_help_linear_layout)!!
    }

    private fun checkVisibility() {
        if (bikliHelperOff.visibility == View.VISIBLE && bikliHelperOn.visibility == View.GONE) {
            bikliHelperOff.visibility = View.GONE
            bikliHelperOn.visibility = View.VISIBLE
        } else if (bikliHelperOff.visibility == View.GONE && bikliHelperOn.visibility == View.VISIBLE) {
            bikliHelperOff.visibility = View.VISIBLE
            bikliHelperOn.visibility = View.GONE
        }
    }
}