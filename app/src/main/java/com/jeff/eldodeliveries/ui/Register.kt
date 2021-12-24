package com.jeff.eldodeliveries.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jeff.eldodeliveries.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var dataBaseReference : DatabaseReference? = null
    var database : FirebaseDatabase? = null

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        dataBaseReference = database!!.reference.child("profile")

        register()


    }

    private fun register(){

        binding.btnRegister.setOnClickListener {

            val inputs = arrayOf(binding.etUser, binding.edEmail, binding.edPass, binding.etPhone)
            var filled = true
            inputs.forEach { input ->
                filled = filled && input.text!!.isNotEmpty()
                if (!filled){
                    input.error = "required"
                }
            }

            auth.createUserWithEmailAndPassword(binding.edEmail.text.toString(),binding.edPass.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        val currentUser = auth.currentUser
                        val currentUserDb = dataBaseReference?.child((currentUser?.uid!!))
                        currentUserDb?.child("Username")?.setValue(binding.etUser.text.toString())
                        currentUserDb?.child("Phone")?.setValue(binding.etPhone.text.toString())

                        startActivity(Intent(this, Dashboard::class.java))

                        Toast.makeText(this@Register,"Registration successful", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        Toast.makeText(this@Register,"Registration failed, please try again!", Toast.LENGTH_LONG).show()
                    }
                }
        }

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finish()
        }
    }
}
