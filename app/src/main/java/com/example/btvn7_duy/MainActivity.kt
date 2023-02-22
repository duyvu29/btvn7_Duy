package com.example.btvn7_duy

import android.app.Activity
import android.content.Intent
import android.icu.text.CaseMap.Fold
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import org.w3c.dom.Text
import java.text.Format

class MainActivity : AppCompatActivity() {
    companion object {
        //Những hằng số sẽ được khai báo ở đây, chúng ta có thể dùng ở mọi chỗ
        const val KEY = "KEY"
        const val TYPE_EDIT = "TYPE_EDIT"
        const val TYPE_ADD = "TYPE_ADD"
    }

    var adater : RcvAdapterMain? = null
    var array  : ArrayList<folder> = ArrayList()
    var imgBack : ImageView? = null
    var txtAdd  : TextView? = null
    var rcvList  : RecyclerView? = null
    var tenthumuc : TextView? = null
    var motathumuc : TextView? = null
    private var startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        val type = result.data?.extras?.getString(KEY)
        if (result.resultCode == Activity.RESULT_OK) {
            if (type == TYPE_ADD) {
                val folder = result.data?.extras?.get("folder") as? folder
                if (folder != null) {
                    array.add(0, folder)
                }
                adater?.notifyDataSetChanged()
            }
            //6 . Nhận dữ liệu
            else if  (type == TYPE_EDIT){
                val folder2 = result.data?.extras?.get("folder") as? folder
                if  (folder2 != null){
                     for (item in array){
                         if (item.id == folder2.id){
                             item.content = folder2.content
                             item.title   = folder2.title
                             break
                         }
                     }
                    adater?.notifyDataSetChanged()
                }

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // ánh xạ
        mapping()
        // adapte của recyclerView main
        setAdapterRcv()
        // hàm sự kiện
        addEvent()

    }

    fun mapping(){
        imgBack = findViewById(R.id.imgBack)
        txtAdd  = findViewById(R.id.txtAddMain)
        rcvList = findViewById(R.id.rcvMain)
        tenthumuc = findViewById(R.id.txtTitleLayout)
        motathumuc  = findViewById(R.id.txtContentLayout)
    }

    fun addEvent(){
        txtAdd?.setOnClickListener{
            var intent = Intent(this,AddActivity::class.java)
            startForResult.launch(intent)

        }
    }

    fun setAdapterRcv(){

        val linearLayoutManager = LinearLayoutManager(this)
        rcvList?.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        rcvList?.addItemDecoration(dividerItemDecoration)
        array.add(folder(1, "Tổng hợp tin tức thời sự", "tổng hợp tin tức thời sự nóng hổi nhất của tất cả các miền trên dất nước"))
        array.add(folder(2, "Do It Your Self", "Sơn Tùng M-TP đẹp trai hát hay"))
        array.add(folder(3, "Cảm hứng sáng tạo","tổng hợp tin tức thời sự nóng hổi nhất của tất cả các miền trên dất nước"))
        array.add(folder(4, "Cảm hứng sáng tạo","tổng hợp tin tức thời sự nóng hổi nhất của tất cả các miền trên dất nước"))
        array.add(folder(5, "Cảm hứng sáng tạo","tổng hợp tin tức thời sự nóng hổi nhất của tất cả các miền trên dất nước"))
        array.add(folder(6, "Cảm hứng sáng tạo","tổng hợp tin tức thời sự nóng hổi nhất của tất cả các miền trên dất nước"))
        adater = RcvAdapterMain(this, array)
        rcvList?.adapter = adater
        //3. chuyển đổi màn hình
        adater?.onItemClick = { folder, position ->

          var intent2= Intent(this, EditActivity::class.java)
              intent2.putExtra("folder",folder)
             Toast.makeText(this,"tc",Toast.LENGTH_SHORT).show()
             startForResult.launch(intent2)



        }

    }


}