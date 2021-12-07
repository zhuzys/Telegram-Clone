package com.example.telegram.utils

import com.example.telegram.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

lateinit var  AUTH :FirebaseAuth
//lateinit var APP_ACTIVITY: MainActivity
const val NODE_USERS = "users"
const val CHILD_PHONE = "phone"
const val CHILD_USERNAME  = "username"
const val CHILD_ID = "id"
lateinit var REF_DATABASE_ROOT: DatabaseReference

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
}