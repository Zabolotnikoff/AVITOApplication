package com.example.avitoapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = CellListAdapter()

        reciclerView.adapter = adapter
        reciclerView.layoutManager =
            GridLayoutManager(this, getResources().getConfiguration().orientation * 2)
//        adapter.notifyDataSetChanged()

        val scope = CoroutineScope(Dispatchers.Main)
        if (!State.started) {
            State.started = true
            scope.async {
                do {
                    delay(5000)
                    adapter.addData()
                } while (true)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}
