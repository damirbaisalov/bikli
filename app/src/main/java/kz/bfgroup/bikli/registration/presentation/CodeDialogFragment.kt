package kz.bfgroup.bikli.registration.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import kz.bfgroup.bikli.R
import kz.bfgroup.bikli.data.ApiRetrofit
import kz.bfgroup.bikli.registration.models.ResponseUserTokenInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val MY_APP = "MY_APP"
const val GENERATED_ACCESS_TOKEN = "ACCESS_TOKEN"
class CodeDialogFragment: DialogFragment() {

    private lateinit var rootView: View

    private lateinit var closeButton: Button
    private lateinit var code1EditText: EditText
    private lateinit var code2EditText: EditText
    private lateinit var code3EditText: EditText
    private lateinit var code4EditText: EditText

    private lateinit var fields: Map<String,String>

    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(R.layout.code_dialoge_fragment,container,false)
        bindViews()

        closeButton.setOnClickListener {
            dismiss()
        }

        codeEditTextLogic()


        code4EditText.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                    val codeConverted : String = code1EditText.text.toString() + code2EditText.text.toString() +
                        code3EditText.text.toString() + code4EditText.text.toString()
                    fields = mutableMapOf(
                        "phone_user" to "+77083046340",
                        "code" to codeConverted
                    )
                    loginUser()

            }
        })


        return rootView
    }

    private fun bindViews() {
        closeButton = rootView.findViewById(R.id.code_dialog_fragment_back_button)
        code1EditText = rootView.findViewById(R.id.code_number_1)
        code2EditText = rootView.findViewById(R.id.code_number_2)
        code3EditText = rootView.findViewById(R.id.code_number_3)
        code4EditText = rootView.findViewById(R.id.code_number_4)

    }

    private fun loginUser() {
        ApiRetrofit.getApiClient().loginUser(fields).enqueue(object : Callback<ResponseUserTokenInfo> {
            override fun onResponse(call: Call<ResponseUserTokenInfo>, response: Response<ResponseUserTokenInfo>) {
                Log.d("user_token_info", response.body().toString())

                if (response.isSuccessful) {
                    Toast.makeText(rootView.context, response.body().toString() + " OK OCCURRED", Toast.LENGTH_LONG).show()
                    val token = response.body()?.response?.token

                    if (token!=null){
                        saveAccessToken(token)
                    }

                }
            }

            override fun onFailure(call: Call<ResponseUserTokenInfo>, t: Throwable) {
                Log.d("user_info_failure_token", t.message.toString())
                Toast.makeText(rootView.context, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun codeEditTextLogic() {
        code1EditText.doOnTextChanged { text, start, before, count ->
            if (count==1) {
                code2EditText.requestFocus()
            }
        }

        code2EditText.doOnTextChanged { text, start, before, count ->
            if (count==1) {
                code3EditText.requestFocus()
            } else {
                code1EditText.requestFocus()
            }
        }

        code3EditText.doOnTextChanged { text, start, before, count ->
            if (count == 1) {
                code4EditText.requestFocus()
            } else {
                code2EditText.requestFocus()
            }
        }

        code4EditText.doOnTextChanged { text, start, before, count ->
            if (count == 0) {
                code3EditText.requestFocus()
            }
        }
    }

    private fun saveAccessToken(token: String) {
        sharedPref = rootView.context.getSharedPreferences(MY_APP, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(GENERATED_ACCESS_TOKEN, token)
        editor.apply()

    }

}