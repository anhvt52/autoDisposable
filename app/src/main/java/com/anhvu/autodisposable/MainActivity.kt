package com.anhvu.autodisposable

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val autoDisposable = AutoDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        autoDisposable.bindTo(this.lifecycle)

        val hello = findViewById<TextView>(R.id.tvHello)
        btnSubmit.setOnClickListener {
            Observable.just("Hello, this text emitted by observable")
                .delay(3000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.i(TAG, "onNext: $it")
                        hello.text = it
                        hello.visibility = View.VISIBLE
                    },
                    { Log.e(TAG, "Error: ${it.message}") }
                )
                .addTo(autoDisposable) //magic here
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
