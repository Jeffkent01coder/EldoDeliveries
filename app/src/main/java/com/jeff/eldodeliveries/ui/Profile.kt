package com.jeff.eldodeliveries.ui
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.*
//import com.jeff.eldodeliveries.databinding.ActivityProfileBinding
//
//class Profile : AppCompatActivity() {
//
//    private lateinit var auth: FirebaseAuth
//    var dataBaseReference : DatabaseReference? = null
//    var database : FirebaseDatabase? = null
//
//    private lateinit var binding: ActivityProfileBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        binding = ActivityProfileBinding.inflate(layoutInflater)
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        auth = FirebaseAuth.getInstance()
//        database = FirebaseDatabase.getInstance()
//        dataBaseReference = database!!.reference.child("profile")
//
//    }
//
//    private fun loadProfile(){
//
//        val user = auth.currentUser
//        val userReference =  dataBaseReference?.child(user?.uid!!)
//
//        binding.edEmai.text = "Email : " +user?.email
//
//        userReference?.addValueEventListener(object: ValueEventListener{
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                binding.etUser.text = "Username : "+snapshot.child("Username").value.toString()
//                binding.etPhone.text = "Phone Number : " + snapshot.child("Phone Number").value.toString()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })
//    }
//}