package com.humnoy.dev.sampleactiveandroid.View

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
import butterknife.ButterKnife
import butterknife.OnClick
import com.activeandroid.query.Delete
import com.activeandroid.query.Select
import com.humnoy.dev.sampleactiveandroid.R
import com.humnoy.dev.sampleactiveandroid.util.ViewUtil
import com.humnoy.dev.sampleactiveandroid.model.Category
import com.humnoy.dev.sampleactiveandroid.model.Item
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
        ButterKnife.bind(this)

        val decorView = window.decorView;
        btnSave = ViewUtil.find(decorView, R.id.btnSave)
        etItem = ViewUtil.find(decorView, R.id.etItem)
        etCategory = ViewUtil.find(decorView, R.id.etCategory)
        recylcer = ViewUtil.find(decorView, R.id.recyclerView)


        btnSave?.setOnClickListener {
            saveData()
        }

        val layoutManager = LinearLayoutManager(this)
        recylcer?.layoutManager = layoutManager

        mAdapter = ItemAdapter()
        recylcer?.adapter = mAdapter

        mAdapter?.list = readDatabaseItem()

        mAdapter?.setOnClickItemListener(object : ItemAdapter.OnItemClickListener {
            override fun onClick(position: Int, item: Item?) {
                Log.d("Main","Test $position  ${item?.name}")
            }
        })

    }


    @OnClick(R.id.btnSave)
    fun saveData(){
//        /*get Text*/
        val itemString = etItem?.text.toString()
        val categoryString = etCategory?.text.toString()
//        //new Object
        val category = Category()
        category.name = categoryString

        val item = Item(name = itemString, category = category)
//        //Save To DataBase
        category.save()
        item.save()
        //Add Data to Adapter
        /*Read*/
        Log.d("Main","save $itemString  $categoryString")

        mAdapter?.list = readDatabaseItem()
        mAdapter?.notifyDataSetChanged()
    }

    fun readDatabaseItem() : List<Item>?{
        return Select().from(Item::class.java).execute()
    }

    /*Inner Class*/
    class ItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        var list : List<Item>? = null
        private var onItemClick : OnItemClickListener? = null

        fun setOnClickItemListener( onItemClick : OnItemClickListener?){
            this.onItemClick = onItemClick;
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            var mHolder : ViewHolder = holder as ViewHolder;

            mHolder.tvItem.text = "Item = ${list?.get(position)?.name}" +
                    "||| category = ${list?.get(position)?.category?.name}"
        }

        override fun getItemCount(): Int {
            return list?.size ?:0
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
            var view : TextView = TextView(parent?.context)
            view.setTypeface(null, Typeface.BOLD)
            view.textSize = 16f
            view.setPadding(10,10,10,10)
            return ViewHolder(view)
        }



        inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
            var tvItem : TextView = itemView as TextView;
            init{
                tvItem.setOnClickListener(this)
            }
            override fun onClick(v: View?) {
                onItemClick?.onClick(adapterPosition, list?.get(adapterPosition))
            }


        }

        interface OnItemClickListener {
            fun onClick(position : Int,item : Item?)
        }
    }
}
