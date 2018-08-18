package com.example.applecustomer.collegemanageementwithroom.acitivities

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.TextView
import com.example.applecustomer.collegemanageementwithroom.ClassViewModel
import com.example.applecustomer.collegemanageementwithroom.R
import com.example.applecustomer.collegemanageementwithroom.models.DepartmentClass

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ClassViewModel
    private val REQUEST_ADD_CLASS = 1

    private var mClasses = mutableListOf<DepartmentClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.classRV)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ClassAdapter(baseContext, mClasses)

        recyclerView.adapter = adapter
      //  viewModel = ClassViewModel(application)
        viewModel = ViewModelProviders.of(this).get(ClassViewModel::class.java)

        viewModel.getAllClasses().observe(this, Observer<List<DepartmentClass>> { classes -> adapter.setClasses(classes!!) })

        fab.setOnClickListener { view ->
            val intent = Intent(this, AddClassActivity::class.java)
            startActivityForResult(intent, REQUEST_ADD_CLASS)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_ADD_CLASS){
            var name = data?.getStringExtra("name")
            var desc = data?.getStringExtra("desc")
            var c = DepartmentClass(name!!, desc!!)
            viewModel.insert(c)

        }
    }


    class ClassHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var classname = itemView.findViewById<TextView>(R.id.classNameTV)
        var classDesc = itemView.findViewById<TextView>(R.id.classDescTV)
        fun bindClass(department: DepartmentClass) {
            classname.text = department.mClassName
            classDesc.text = department.mClassDesc
            itemView.setOnClickListener {
              var intent = ListStudent.getStartIntent(itemView.context , department.mClassId!!)
                itemView.context.startActivity(intent)

            }

        }



    }

    class ClassAdapter(mContext: Context, private var mClasses: List<DepartmentClass>) : RecyclerView.Adapter<ClassHolder>() {

        var mContext: Context? = mContext


        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ClassHolder {
            val view = LayoutInflater.from(mContext).inflate(R.layout.item_class, parent, false)
            return ClassHolder(view)

        }

        override fun getItemCount(): Int {
            return mClasses.size

        }

        override fun onBindViewHolder(holder: ClassHolder, position: Int) {
            val department = mClasses[position]
            holder.bindClass(department)

        }

        fun setClasses(classes: List<DepartmentClass>) {

            mClasses = classes
            notifyDataSetChanged()
        }

    }


}
