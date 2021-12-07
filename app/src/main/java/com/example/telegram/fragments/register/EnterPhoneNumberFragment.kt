package com.example.telegram.fragments.register

import androidx.fragment.app.Fragment
import android.widget.Toast
import com.example.telegram.MainActivity
import com.example.telegram.R
import com.example.telegram.activities.RegisterActivity
import com.example.telegram.utils.*
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_phone_number.*
import java.util.concurrent.TimeUnit

class EnterPhoneNumberFragment : Fragment(R.layout.fragment_enter_phone_number) {

    private lateinit var mPhoneNumber: String
   private lateinit var mCallback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
 //  private lateinit var mAuth: FirebaseAuth

    override fun onStart() {
        super.onStart()
        AUTH = FirebaseAuth.getInstance()
        //* Callback который возвращает результат верификации *//*
        mCallback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            //проверяет есть ли верификация
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
               // *//* Функция срабатывает если верификация уже была произведена,
               // * пользователь авторизируется в приложении без потверждения по смс *//*
               AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Добро пожаловать")
                       // restartActivity()
                        //нам надо запустить main Activity
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else showToast(task.exception?.message.toString())
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                //*//* Функция срабатывает если верификация не удалась*//*
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
             //   *//* Функция срабатывает если верификация впервые, и отправлена смс *//*
                replaceFragment(
                    EnterCodeFragment(
                        mPhoneNumber,
                        id
                    )
                )
            }
        }
    register_btn_next.setOnClickListener { sendCode() }
}



    private fun sendCode() {
        /* Функция проверяет поле для ввода номер телефона, если поле пустое выводит сообщение.
         * Если поле не пустое, то начинает процедуру авторизации/ регистрации */
        if (register_input_phone_number.text.toString().isEmpty()) {
            Toast.makeText(activity, getString(R.string.register_toast_enter_phone), Toast.LENGTH_SHORT).show()
         //   showToast(getString(R.string.register_toast_enter_phone))
        } else {
         authUser()
          //  replaceFragment(EnterCodeFragment(mPhoneNumber, id))
        }
    }





      private fun authUser() {
          //* Инициализация *//*
        mPhoneNumber = register_input_phone_number.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber,
            60,
            TimeUnit.SECONDS,
            APP_ACTIVITY,
            mCallback
        )
    }

}
