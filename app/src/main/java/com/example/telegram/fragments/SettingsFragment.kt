package com.example.telegram.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.telegram.MainActivity
import com.example.telegram.R
import com.example.telegram.activities.RegisterActivity
import com.example.telegram.databinding.FragmentChatsBinding
import com.example.telegram.databinding.FragmentSettingsBinding
import com.example.telegram.utils.AUTH
import com.example.telegram.utils.replaceActivity

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {
private lateinit var binding : FragmentSettingsBinding


override fun onResume() {
    super.onResume()
    setHasOptionsMenu(true)

}

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.settings_menu_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
        }
       return  true
    }
}