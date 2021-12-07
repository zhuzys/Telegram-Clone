package com.example.telegram.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.telegram.R
//класс от которого можно наследоваться
open class BaseFragment(val layout: Int):Fragment(layout) {

    override fun onStart() {
        super.onStart()
    }
}