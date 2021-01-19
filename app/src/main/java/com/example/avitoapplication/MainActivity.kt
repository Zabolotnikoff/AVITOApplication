package com.example.avitoapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = CellListAdapter()

        reciclerView.adapter = adapter
        reciclerView.layoutManager =
            GridLayoutManager(this, getResources().getConfiguration().orientation * 2)

        val scope = CoroutineScope(coroutineContext)
        scope.launch {
            do {
                delay(5000)
                adapter.addData()
            } while (true)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}
