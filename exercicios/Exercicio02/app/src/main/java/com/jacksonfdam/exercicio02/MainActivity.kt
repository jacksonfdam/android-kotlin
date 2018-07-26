package com.jacksonfdam.exercicio02

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
/*
* Olhe tambem: https://medium.com/@biratkirat/4-starting-activities-in-kotlin-ae3642126d3

  https://github.com/googlesamples/android-NavigationDrawer/blob/master/kotlinApp/Application/src/main/java/com/example/android/navigationdrawer/MainActivity.kt

*/

class MainActivity : AppCompatActivity() {

    /*private static final String LOG_TAG = MainActivity.class.getSimpleName();*/
    private val LOG_TAG = MainActivity::class.java.simpleName

    /*public static final String MENSAGEM = "com.jacksonfdam.exercicio02.extra.MESSAGE";*/
    val MENSAGEM = "com.jacksonfdam.exercicio02.extra.MESSAGE"
    // 01 - private var button: Button? = null
    // 02 - private lateinit var button: Button
    private val button by bind<Button>(R.id.button_main) // private val button: Button by bind(R.id.button_main)

    private var mMessageEditText: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMessageEditText = findViewById(R.id.editText_main)
        // 01 || 02 - button = findViewById(R.id.button_main) as Button


        titulo.text = "Hello Kotlin!"

    }

    fun abreAtividade2(view: View) {
        Log.d(LOG_TAG, "Clicou em enviar")
        val value: String = mMessageEditText?.text.toString() // or just your string
        val intent = Intent(this, Tela02Activity::class.java)
        intent.putExtra(MENSAGEM, value)
        startActivity(intent)
    }
}
