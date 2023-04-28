package com.pritampattanayak.tutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class ReadActivity : AppCompatActivity() {

    lateinit var readProgressLayout:RelativeLayout
    lateinit var readProgressBar:ProgressBar
    lateinit var recyclerView:RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerAdapter: ReadRecyclerAdapter
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        readProgressLayout=findViewById(R.id.readProgressLayout)
        readProgressBar=findViewById(R.id.readProgressBar)
        recyclerView=findViewById(R.id.recyclerView)
        layoutManager= LinearLayoutManager(this)

        val bookList= arrayListOf<Book>()

        val queue=Volley.newRequestQueue(this)
        val url="https://script.google.com/macros/s/AKfycbxwop4CsnSVjGjkrtBeFXrT3uQZbyUEO0cR-AVVp545_yOzSV2xTsbx98Na37UlBavn/exec"
        val jsonObjectRequest=object :JsonObjectRequest(
            Request.Method.GET,url,null,
            Response.Listener {
                readProgressLayout.visibility=View.GONE
                readProgressBar.visibility=View.GONE
                val data=it.getJSONArray("bookList")
                for(i in 0 until data.length()){
                    val bookJasonObject=data.getJSONObject(i)
                    val bookObject=Book(
                        bookJasonObject.getString("itemName"),
                        bookJasonObject.getString("itemAuthor"),
                        bookJasonObject.getInt("itemPrice"),
                        bookJasonObject.getString("itemRating")
                    )
                    bookList.add(bookObject)
                }
                recyclerAdapter= ReadRecyclerAdapter(this,bookList)
                recyclerView.adapter=recyclerAdapter
                recyclerView.layoutManager=layoutManager
            },Response.ErrorListener {
                readProgressLayout.visibility=View.GONE
                readProgressBar.visibility=View.GONE
                Toast.makeText(this@ReadActivity, it.toString(), Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getHeaders(): MutableMap<String, String> {
                return super.getHeaders()
            }
        }
        queue.add(jsonObjectRequest)
    }
}