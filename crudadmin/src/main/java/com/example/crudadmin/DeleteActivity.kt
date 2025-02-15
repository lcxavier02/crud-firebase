package com.example.crudadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudadmin.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val vehicleNumber = binding.deleteVehicleNumber.text.toString()
            if (vehicleNumber.isNotEmpty()) {
                deleteData(vehicleNumber)
            } else {
                Toast.makeText(this, "Enter a vehicle number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(vehicleNumber: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener {
            binding.deleteVehicleNumber.text.clear()
            Toast.makeText(this, "Data deleted!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@DeleteActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Unable to delete!", Toast.LENGTH_SHORT).show()
        }
    }
}