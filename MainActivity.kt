package com.pritampattanayak.tutorial
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity()
{

    lateinit var btnRead:Button
    lateinit var btnWrite:Button

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRead=findViewById(R.id.btnRead)
        btnWrite=findViewById(R.id.btnWrite)

        btnRead.setOnClickListener{
            val intent= Intent(this@MainActivity,ReadActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_bottom,R.anim.slide_out_bottom)
            finish()
        }
        btnWrite.setOnClickListener {
            val intent= Intent(this@MainActivity,WriteActivity::class.java)
            startActivity(intent)
        }
    }
}