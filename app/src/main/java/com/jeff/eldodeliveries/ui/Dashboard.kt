package com.jeff.eldodeliveries.ui

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jeff.eldodeliveries.R
import com.jeff.eldodeliveries.adapter.ElRvAdapter
import com.jeff.eldodeliveries.database.Items
import com.jeff.eldodeliveries.databinding.ActivityDashboardBinding
import com.jeff.eldodeliveries.databinding.EldoAddDialogBinding
import com.jeff.eldodeliveries.viewmodel.El_ViewModel

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Hello Moris")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }


        binding.FabAdd.setOnClickListener {

        }
        openDialog()

    }


    fun openDialog(){
        val dialogBinding = EldoAddDialogBinding.inflate(layoutInflater)
        val dialog  = Dialog(this)
        dialog.setContentView(dialogBinding.root)


        val cancelBtn = findViewById<Button>(R.id.idBtnCancel)
        val addBtn  = findViewById<Button>(R.id.idBtnAdd) as Button
        val itemEdit = findViewById<EditText>(R.id.idEditItemName)
        val destnation = findViewById<EditText>(R.id.idLocation)
        val location = findViewById<EditText>(R.id.idDestination)


        addBtn.setOnClickListener {
            val itemName : String = dialogBinding.idEditItemName.text.toString().trim()
            val location : String = dialogBinding.idLocation.text.toString().trim()
            val destination : String = dialogBinding.idDestination.text.toString().trim()

            //val qty :Int = itemQuantity.toInt()
            //val pr : Int = itemPrice.toInt()

            if (itemName.isNotEmpty() && location.isNotEmpty() && destination.isNotEmpty()){

                val items = Items(itemName, location, destination)
                El_ViewModel.insert(items)
                Toast.makeText(applicationContext,"Item Inserted ", Toast.LENGTH_SHORT).show()
                ElRvAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }else{
                Toast.makeText(applicationContext,"Please Enter all the data ", Toast.LENGTH_SHORT).show()

            }
            Toast.makeText(this, "Add btn clicked", Toast.LENGTH_SHORT).show()
        }

        dialogBinding.idBtnCancel.setOnClickListener {
            dialog.dismiss()
        }
        binding.id.setOnClickListener {


        }
        dialog.show()

    }

}