package com.jacksonfdam.exercicio01

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    /*

    https://developer.android.com/guide/components/activities/intro-activities

    https://developer.android.com/guide/components/activities/activity-lifecycle

     */
    /*private static final String TAG = MainActivity.class.getSimpleName();*/
    private val TAG = MainActivity::class.java.simpleName


    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onRestart")
    }
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /*
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
    */

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    /*
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }
    */

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    /*
     @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
    */

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart")
    }

    /*
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
     */

    override fun onStop() {
        // call the superclass method first
        super.onStop()
    }


    /*
     @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    */

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}
