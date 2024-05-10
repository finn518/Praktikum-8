package com.example.praktikum_6

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikum_6.database.DB
import com.example.praktikum_6.database.spendData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val data: MutableList<spendData> = mutableListOf()
    private lateinit var rv: RecyclerView
    private lateinit var adapter: SpendAdapter
    val db by lazy { DB(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rv = findViewById(R.id.rv)
        val addForm: LinearLayout = findViewById(R.id.addForm)
        val submit: Button = findViewById(R.id.submit)
        val etName: EditText = findViewById(R.id.etName)
        val etValue: EditText = findViewById(R.id.etValue)


        adapter = SpendAdapter(this, data)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        val add: ImageButton = findViewById(R.id.imageButton)
        add.setOnClickListener(){

            if (rv.visibility == View.VISIBLE){
                rv.visibility = View.GONE
                addForm.visibility = View.VISIBLE
                submit.setOnClickListener() {
                    val inName = etName.text.toString()
                    val inValue = etValue.text.toString()
                    if (inName.isEmpty() && inValue.isEmpty()){
                        Toast.makeText(this, "Tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    }else {
                        CoroutineScope(Dispatchers.IO).launch {
                            db.Dao().addSpend(spendData(0, inName,inValue))
                            val list = db.Dao().getSpend()
                            withContext(Dispatchers.Main){
                                adapter.setSpendData(list)
                            }
                        }
                        etValue.text.clear()
                        etName.text.clear()
                        rv.visibility = View.VISIBLE
                        addForm.visibility = View.GONE
                    }
                }
            } else {
                rv.visibility = View.VISIBLE
                addForm.visibility = View.GONE
            }
        }


    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val list = db.Dao().getSpend()
            withContext(Dispatchers.Main){
                adapter.setSpendData(list)
            }
        }
    }

}
