package com.udacity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.w3c.dom.Text

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        val filename = findViewById<TextView>(R.id.text_filename)
        val status = findViewById<TextView>(R.id.text_status)

        val extras = intent.extras

        filename.text = extras?.getString("filename")
        status.text = extras?.getString("status")
    }
}
