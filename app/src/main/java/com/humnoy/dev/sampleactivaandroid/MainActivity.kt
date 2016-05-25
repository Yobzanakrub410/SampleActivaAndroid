package com.humnoy.dev.sampleactivaandroid

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.activeandroid.query.Select
import java.util.*

class MainActivity : AppCompatActivity() {

    var etItem : EditText? = null
    var etCategory : EditText? = null
    var recylcer : RecyclerView? = null

    var mAdapter : ItemAdapter? = null

    var btnSave : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ButterKnife.bind(this)

        btnSave = findViewById(R.id.btnSave) as Button
        etItem = findViewById(R.id.etItem) as EditText
        etCategory = findViewById(R.id.etCategory) as EditText
        recylcer = findViewById(R.id.recyclerView) as RecyclerView?

        btnSave?.setOnClickListener {
            saveData()
        }

        val layoutManager = LinearLayoutManager(this)
        recylcer?.layoutManager = layoutManager

        mAdapter = ItemAdapter()
        recylcer?.adapter = mAdapter

        mAdapter?.list = readDatabaseItem()


    }

    @OnClick(R.id.btnSave)
    fun saveData(){
//        /*get Text*/
        val itemString = etItem?.text.toString()
        val categoryString = etCategory?.text.toString()
//        //new Object
        val category = Category()
        category.name = categoryString

        val item = Item(name = itemString,category = category)
//        //Save To DataBase
        category.save()
        item.save()
        //Add Data to Adapter
        /*Read*/
        Log.d("Main","save $itemString  $categoryString")

        mAdapter?.notifyDataSetChanged()
        mAdapter?.list = readDatabaseItem()
    }

    fun readDatabaseItem() : List<Item>?{
        return Select().from(Item::class.java).orderBy("Name ASC").execute()
    }

    /*Inner Class*/
    class ItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        var list : List<Item>? = null

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            var h : ViewHolder = holder as ViewHolder;
            h.tvItem.setText("Item = ${list?.get(position)?.name}" +
                    "||| category = ${list?.get(position)?.category?.name}")
        }

        override fun getItemCount(): Int {
            return list?.size ?:0
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
            var view : TextView = TextView(parent?.context)
            view.setTypeface(null,Typeface.BOLD)
            view.setTextSize(16f)
            return ViewHolder(view)
        }

        class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
            var tvItem : TextView = itemView as TextView;
        }

    }
}
