package com.example.btvn7_duy

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

// màn hình edit
class EditActivity : AppCompatActivity() {
    var cacel: TextView?= null
    var save: TextView?= null
    var edtNameEdit: EditText? = null
    var edtDescEdit: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        // mapping
        mapping()
        // 4. set folder ép kiểu thành Folder
        val folderr = intent.extras?.get("folder") as? folder
        if (folderr != null) {
            edtNameEdit?.setText(folderr.title)
            edtDescEdit?.setText(folderr.content)
        }
        //5. Thực hiện chức năng khi ấn vào buttion và đẩy dữ liệu qua main
        save?.setOnClickListener {
            //?:"", nghĩa là khi edttitle?.text?.toString() bị null thì ta để title là rỗng
            //Set lại các giá trị title, content cho biến folder để truyền lại về màn 1
            folderr?.title = edtNameEdit?.text?.toString() ?:""
            folderr?.content = edtDescEdit?.text?.toString() ?:""
            var intent : Intent = Intent()
            intent.putExtra("folder", folderr)
            intent.putExtra(MainActivity.KEY, MainActivity.TYPE_EDIT)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun mapping() {
        cacel           = findViewById(R.id.txtCancelLayoutEdit)
        save            = findViewById(R.id.txtSaveLayoutEdit)
        edtNameEdit     = findViewById(R.id.edtEditNameLayout)
        edtDescEdit     = findViewById(R.id.edtEditDescLayout)
    }
}