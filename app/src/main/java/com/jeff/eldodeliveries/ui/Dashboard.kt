package com.jeff.eldodeliveries.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.jeff.eldodeliveries.R
import com.jeff.eldodeliveries.databinding.ActivityDashboardBinding
import com.jeff.eldodeliveries.ui.adapter.MyAdapter


class Dashboard : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<Information>

    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT,"Hello Moris")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent,null)
            startActivity(shareIntent)
        }

        binding.Add.setOnClickListener {
            startActivity(Intent(this, Items::class.java))
        }

        userRecyclerView = findViewById(R.id.rcinfo)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<Information>()

        getInfo()

    }

    private fun getInfo() {

        databaseReference = FirebaseDatabase.getInstance().getReference("Information")
        databaseReference.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val user = userSnapshot.getValue(Information::class.java)
                        userArrayList.add(user!!)
                    }

                    userRecyclerView.adapter = MyAdapter(userArrayList)
                }

            }

            override fun onCancelled(error: DatabaseError) {

            }


        })
    }
}