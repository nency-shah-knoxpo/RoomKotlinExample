package com.example.applecustomer.collegemanageementwithroom.acitivities

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.applecustomer.collegemanageementwithroom.R

class AddClassActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_class)

        var saveBtn = findViewById<Button>(R.id.saveClassBtn)
        val classname = findViewById<EditText>(R.id.classNameET)
        val classDesc = findViewById<EditText>(R.id.classDescET)

        saveBtn.setOnClickListener {

            var intent = Intent()
            intent.putExtra("name",classname.text.toString())
            intent.putExtra("desc",classDesc.text.toString())
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }


}
