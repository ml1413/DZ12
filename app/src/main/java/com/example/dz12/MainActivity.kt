package com.example.dz12

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.stream.Collectors

class MainActivity : AppCompatActivity() {
    private lateinit var etMail: EditText
    private lateinit var etThem: EditText
    private lateinit var etMessage: EditText
    private lateinit var ivSend: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        ivSend.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
            intent.putExtra(Intent.EXTRA_EMAIL, getListEmail(etMail.text.toString()))
            intent.putExtra(Intent.EXTRA_SUBJECT, etThem.text.toString())
            intent.putExtra(Intent.EXTRA_TEXT, etMessage.text.toString())
            startActivity(intent)
        }
    }

    private fun init() {
        etMail = findViewById(R.id.et_mail)
        etThem = findViewById(R.id.et_them)
        etMessage = findViewById(R.id.et_message)
        ivSend = findViewById(R.id.iv_send)
    }

    private fun getListEmail(string: String): Array<String> {
        return string.split(regex = "[\\s+,]".toRegex()).stream()
            .filter { it.contains("@") }
            .map {
                return@map when {
                    it.first().toString().contains("\\W".toRegex()) -> {
                        it.substring(1)
                    }

                    it.last().toString().contains("\\W".toRegex()) -> {
                        it.substring(0, it.lastIndex)
                    }

                    else -> return@map it
                }
            }.collect(Collectors.toList()).toTypedArray()
    }
}