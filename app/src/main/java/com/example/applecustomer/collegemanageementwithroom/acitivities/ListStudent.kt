package com.example.applecustomer.collegemanageementwithroom.acitivities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.applecustomer.collegemanageementwithroom.ClassViewModel
import com.example.applecustomer.collegemanageementwithroom.EditStudent
import com.example.applecustomer.collegemanageementwithroom.R
import com.example.applecustomer.collegemanageementwithroom.StudentViewModel
import com.example.applecustomer.collegemanageementwithroom.models.DepartmentClass
import com.example.applecustomer.collegemanageementwithroom.models.Student
import kotlinx.android.synthetic.main.activity_list_student.*
import kotlinx.android.synthetic.main.activity_main.*

private val EXTRA_CLASS_ID = "EXTRA_CLASS_ID"
private val REQUEST_ADD_STUDENT = 0
private val REQUEST_EDIT_STUDENT = 1


class ListStudent : AppCompatActivity() {

var classId : Int? = null
    companion object {
        fun getStartIntent(
                context: Context, classId: Int
        ): Intent {
            val intent = Intent(context, ListStudent::class.java)
            intent.putExtra(EXTRA_CLASS_ID, classId)

            return intent
        }
    }

    lateinit var viewModel: StudentViewModel


    private var mStudents = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student)

        val recyclerView = findViewById<RecyclerView>(R.id.studentRV)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ListStudent.StudentAdapter(baseContext, mStudents)
         classId = intent.getIntExtra(EXTRA_CLASS_ID, -1)
        recyclerView.adapter = adapter
      //  viewModel = StudentViewModel(application)
        viewModel = ViewModelProviders.of(this).get(StudentViewModel::class.java)

        viewModel.getAllStudents(classId!!).observe(this, Observer<List<Student>> { students -> adapter.setStudents(students!!) })


        addStudentFAB.setOnClickListener { view ->
            val intent = Intent(this, AddStudent::class.java)
            startActivityForResult(intent, REQUEST_ADD_STUDENT)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_ADD_STUDENT){
            var name = data?.getStringExtra("student_name")
            var c = Student(name!!, classId!!)
            viewModel.insertStudent(c)

        }

        else if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_EDIT_STUDENT){

            var studentId = data?.getIntExtra("student_id",0)
            var classId = data?.getIntExtra("class_id",0)
            var name = data?.getStringExtra("updated_student_name")
            viewModel.updateStudent(studentId!!,classId!!,name!!)
        }
    }


    class StudentHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var studentName = itemView.findViewById<TextView>(R.id.studentNameTV)
        fun bindStudent(student: Student) {
            studentName.text = student.mStudentName

            itemView.setOnClickListener {
                var intent = EditStudent.getStartIntent(itemView.context,studentId = student.mStudentId!!,classId = student.mClassId!!)

                (itemView.context as Activity).startActivityForResult(intent, REQUEST_EDIT_STUDENT)


            }
        }


    }

    class StudentAdapter(mContext: Context, private var mStudents: List<Student>) : RecyclerView.Adapter<StudentHolder>() {

        var mContext: Context? = mContext


        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StudentHolder {
            val view = LayoutInflater.from(mContext).inflate(R.layout.item_student, parent, false)
            return StudentHolder(view)

        }

        override fun getItemCount(): Int {
            return mStudents.size

        }

        override fun onBindViewHolder(holder: StudentHolder, position: Int) {
            val student = mStudents[position]
            holder.bindStudent(student)

        }

        fun setStudents(students: List<Student>) {

            mStudents = students
            notifyDataSetChanged()
        }

    }

}
