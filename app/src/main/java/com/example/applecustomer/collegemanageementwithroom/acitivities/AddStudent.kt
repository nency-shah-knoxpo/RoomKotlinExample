package com.example.applecustomer.collegemanageementwithroom.acitivities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.example.applecustomer.collegemanageementwithroom.R
import com.example.applecustomer.collegemanageementwithroom.models.Student
import kotlinx.android.synthetic.main.activity_add_class.*

class AddStudent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        var saveBtn = findViewById<Button>(R.id.saveStudentBtn)
        val studentName = findViewById<EditText>(R.id.studentNameET)

        saveBtn.setOnClickListener {

            var intent = Intent()
            intent.putExtra("student_name",studentName.text.toString())
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }

}
