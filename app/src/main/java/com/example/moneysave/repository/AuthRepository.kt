package com.example.moneysave.repository

import com.google.firebase.auth.FirebaseAuth

class AuthRepository(
    private val firebaseAuth: FirebaseAuth
) {

    fun login(email: String, password: String) =
        firebaseAuth.signInWithEmailAndPassword(email, password)

    fun register(email: String, password: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password)

    fun logout() = firebaseAuth.signOut()
}

