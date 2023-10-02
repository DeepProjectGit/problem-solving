package com.example.precticaltask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.precticaltask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            try {
                if(binding.edRow.text.toString().toInt() > 0 || binding.edCol.text.toString().toInt() > 0 ){
                    val getIntent = Intent(this@MainActivity,SecondActivity::class.java)
                    getIntent.putExtra("col",binding.edCol.text.toString().toInt())
                    getIntent.putExtra("row",binding.edRow.text.toString().toInt())
                    startActivity(getIntent)
                }
            }catch (e:Exception){
              Toast.makeText(this@MainActivity,"Something wrong",Toast.LENGTH_SHORT).show()
            }

        }
    }
}