package com.jeff.eldodeliveries.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jeff.eldodeliveries.databinding.ActivityItemsBinding

class Items : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var binding : ActivityItemsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityItemsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.btnRequest.setOnClickListener {
            if (binding.tvLocation.text!!.isEmpty()
                || binding.tvDestination.text!!.isEmpty()
                || binding.tvItem.text!!.isEmpty()
                || binding.tvAmount.text!!.isEmpty()){
                Toast.makeText(this@Items,"all fields required",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                request()
            }
        }


    }

    private fun request() {
        database = FirebaseDatabase.getInstance().getReference("Information")
        val location = binding.tvLocation.text.toString()
        val destination = binding.tvDestination.text.toString()
        val item = binding.tvItem.text.toString()
        val amount = binding.tvAmount.text.toString()

        val info = Information(location,destination,item,amount)
        database.child("Requests").setValue(info)

            .addOnSuccessListener {

            binding.tvLocation.text!!.clear()
            binding.tvDestination.text!!.clear()
            binding.tvItem.text!!.clear()
            binding.tvAmount.text!!.clear()

            Toast.makeText(this, "Requested Successfully", Toast.LENGTH_SHORT).show()

        }
            .addOnFailureListener {

                Toast.makeText(this,"Failed to Request", Toast.LENGTH_SHORT).show()
            }
        //val databaseReference = database.reference.child("Information")


    }


}