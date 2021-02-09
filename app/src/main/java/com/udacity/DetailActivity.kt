package com.udacity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.w3c.dom.Text
import java.lang.NullPointerException

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        val filename = findViewById<TextView>(R.id.text_filename)
        val status = findViewById<TextView>(R.id.text_status)
        val okButton = findViewById<Button>(R.id.ok_button)

        val extras = intent.extras



        extras?.let {
            filename.text = it.getString("filename")

            when (it.getString("status")) {
                "Success" -> status.setTextColor(resources.getColor(R.color.colorPrimaryDark, null))
                else  -> status.setTextColor(resources.getColor(R.color.colorFailed, null))
            }
        }

        status.text = extras?.getString("status")

        okButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
