package com.example.telegram.fragments.register

import androidx.fragment.app.Fragment
import com.example.telegram.MainActivity
import com.example.telegram.R
import com.example.telegram.activities.RegisterActivity
import com.example.telegram.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCodeFragment(val mPhoneNumber: String, val id: String) : Fragment(R.layout.fragment_enter_code) {
 //   private lateinit var mAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = mPhoneNumber
        register_input_code.addTextChangedListener(AppTextWatcher  {
                val string: String = register_input_code.text.toString()
                if(string.length == 6)
                   ///verificateCode()
                       enterCode();

            }
    )

}

    private fun enterCode() {
        val code = register_input_code.text.toString()
       val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String , Any>()
                dateMap[CHILD_ID]=  uid
                dateMap[CHILD_PHONE] = mPhoneNumber
                dateMap[CHILD_USERNAME] = uid

                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap).addOnCompleteListener{task2->
                    if(task2.isSuccessful){
                        showToast("Добро пожаловать")
                        (activity as RegisterActivity).replaceActivity(MainActivity())

                    }else
                        showToast(task2.exception?.message.toString())

                }

                // restartActivity()
                //нам надо запустить main Activity
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else showToast(task.exception?.message.toString())
        }
    }


    fun verificateCode() {
       showToast("Ok")
    }
}