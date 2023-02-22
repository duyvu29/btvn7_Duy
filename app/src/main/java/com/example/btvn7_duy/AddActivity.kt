package com.example.btvn7_duy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text
import java.sql.Savepoint

// màn hình thêm
class AddActivity : AppCompatActivity() {

    lateinit var txtCancel      : TextView
    lateinit var txtSavepoint   : TextView
    lateinit var edtNameFolder  : EditText
    lateinit var edtDeescFolder : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        // ánh xạ
        mapping()
        //
        ResulfDataa()
    }

    private fun mapping() {
        txtCancel       = findViewById(R.id.txtCancelLayoutAdd)
        txtSavepoint    =findViewById(R.id.txtSaveLayoutAdd)
        edtDeescFolder  =findViewById(R.id.edtMota)
        edtNameFolder   = findViewById(R.id.edtTenthumuc)
    }
    // hàm truyền dũ liệu về MainAcivity

    fun ResulfDataa(){


        txtSavepoint?.setOnClickListener{

            val folder = folder(
                id = System.currentTimeMillis().toInt(),
                title = edtNameFolder?.text.toString(),
                content = edtDeescFolder?.text.toString()
            )

            var intent = Intent()
            intent.putExtra("folder",folder)
            intent.putExtra(MainActivity.KEY,MainActivity.TYPE_ADD)
            setResult(RESULT_OK,intent)
            finish()
        }


    }
}