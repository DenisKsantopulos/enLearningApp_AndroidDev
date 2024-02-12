package com.example.firstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val view = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button2)

        button.setOnClickListener{
            view.text= (view.text.toString().toInt()+1).toString()
        }
    }

    fun toastMe(view: View){
        val myToast = Toast.makeText(this,"Hello Toast!",Toast.LENGTH_SHORT)

        myToast.show()
    }
}