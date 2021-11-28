package com.jeff.eldodeliveries.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.jeff.eldodeliveries.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        login()
    }

    private fun login() {

        binding.btnLogin.setOnClickListener {

            val inputs = arrayOf(binding.edEmail, binding.etPass)
            var filled = true
            inputs.forEach { input ->
                filled = filled && input.text!!.isNotEmpty()
                if (!filled) {
                    input.error = "required"
                }
            }

            auth.signInWithEmailAndPassword(binding.edEmail.text.toString(), binding.etPass.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this@Login,"Login Succesfull!", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,Dashboard::class.java))
                        finish()
                    }else{
                        Toast.makeText(this@Login,"Login failed, please try again!", Toast.LENGTH_LONG).show()
                    }
                }

        }
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
            finish()
        }
    }
}