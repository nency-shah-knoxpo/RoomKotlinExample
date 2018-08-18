package com.example.applecustomer.collegemanageementwithroom.Repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.applecustomer.collegemanageementwithroom.ClassDataBase
import com.example.applecustomer.collegemanageementwithroom.DAO.ClassDAO
import com.example.applecustomer.collegemanageementwithroom.DAO.StudentDAO
import com.example.applecustomer.collegemanageementwithroom.models.DepartmentClass
import com.example.applecustomer.collegemanageementwithroom.models.Student

class ClassRepository {


    private var mClassDAO: ClassDAO? = null
    private lateinit var mAllClasses: LiveData<List<DepartmentClass>>




    constructor(application: Application){
        val db = ClassDataBase.getClassDataBase(application)

        this.mClassDAO = db!!.classDAO()
        this.mAllClasses = mClassDAO!!.getAllClasses()


    }


    internal fun getAllClasses(): LiveData<List<DepartmentClass>> {
        return mAllClasses
    }


    fun addClass(department: DepartmentClass) {
        insertAsyncTask(mClassDAO!!).execute(department)

    }



    private class insertAsyncTask(private val mAsyncTaskDAO: ClassDAO) : AsyncTask<DepartmentClass, Void, Void>() {

        override fun doInBackground(vararg department: DepartmentClass): Void? {
            mAsyncTaskDAO.addClass(department[0])
            return null
        }
    }

}