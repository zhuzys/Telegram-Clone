package com.example.telegram.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import com.example.telegram.R
import com.example.telegram.databinding.ActivityRegisterBinding
import com.example.telegram.fragments.register.EnterPhoneNumberFragment
import com.example.telegram.utils.replaceActivity
import com.example.telegram.utils.replaceFragment

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var mToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        setSupportActionBar(binding.registerToolbar)
        title = getString(R.string.register_title_your_phone)
        replaceFragment(EnterPhoneNumberFragment(), false)
       /* supportFragmentManager.beginTransaction()
            .add(R.id.data_container, EnterPhoneNumberFragment())
            .commit()*/
    }
}