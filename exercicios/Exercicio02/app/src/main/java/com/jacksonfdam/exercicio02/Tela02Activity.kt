package com.jacksonfdam.exercicio02

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Tela02Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela02)

        var bundle :Bundle ?=intent.extras
        var message = bundle!!.getString("com.jacksonfdam.exercicio02.extra.MESSAGE") // 1
        var strMensagem: String = intent.getStringExtra("com.jacksonfdam.exercicio02.extra.MESSAGE") // 2
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
