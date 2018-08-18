package com.example.applecustomer.collegemanageementwithroom

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.applecustomer.collegemanageementwithroom.acitivities.ListStudent
import com.example.applecustomer.collegemanageementwithroom.models.Student
import kotlinx.android.synthetic.main.activity_edit_student.*

private val EXTRA_STUDENT_ID = "EXTRA_STUDENT_ID"

private val EXTRA_CLASS_ID = "EXTRA_CLASS_ID"

class EditStudent : AppCompatActivity() {
    lateinit var viewModel: StudentViewModel
    companion object {
        fun getStartIntent(
                context: Context, studentId: Int, classId: Int
        ): Intent {
            val intent = Intent(context, EditStudent::class.java)
            intent.putExtra(EXTRA_STUDENT_ID, studentId)
            intent.putExtra(EXTRA_CLASS_ID, classId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)
        var i = intent.getIntExtra(EXTRA_STUDENT_ID,-1)
        var j = intent.getIntExtra(EXTRA_CLASS_ID,-1)
        var name = findViewById<EditText>(R.id.updateedStudentNameET)
        updateStudentBtn.setOnClickListener {

            var intent = Intent()

            intent.putExtra("updated_student_name",name.text.toString())
            intent.putExtra("student_id",i)
            intent.putExtra("class_id",j)
            setResult(Activity.RESULT_OK,intent)
            finish()

        }
    }




}
