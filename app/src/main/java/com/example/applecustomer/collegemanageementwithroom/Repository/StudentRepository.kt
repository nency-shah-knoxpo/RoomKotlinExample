package com.example.applecustomer.collegemanageementwithroom.Repository

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.applecustomer.collegemanageementwithroom.ClassDataBase
import com.example.applecustomer.collegemanageementwithroom.DAO.StudentDAO
import com.example.applecustomer.collegemanageementwithroom.models.Student

class StudentRepository {


    private var mStudentDAO: StudentDAO? = null
    private lateinit var mAllStudents: LiveData<List<Student>>
var sId :Int? = null
    var cId : Int? = null
    var updatedname : String? = null

    constructor(application: Application){
        val db = ClassDataBase.getClassDataBase(application)
        this.mStudentDAO = db?.studentDAO()

    }

    internal fun getAllStudent(classId: Int): LiveData<List<Student>> {
        return mStudentDAO!!.getAllStudents(classId)
    }

    fun updateStudent(studentId:Int,classId:Int,name:String){
        sId = studentId
        cId= classId
        updatedname = name


        updateStudentAsyncTask(mStudentDAO!!).execute(name)

    }
    fun addStudent(student: Student){
        StudentRepository.insertStudentAsyncTask(mStudentDAO!!).execute(student)
    }


    private class insertStudentAsyncTask(private val mAsyncTaskDAO: StudentDAO) : AsyncTask<Student, Void, Void>() {

        override fun doInBackground(vararg student: Student): Void? {
            mAsyncTaskDAO.addStudent(student[0])
            return null
        }
    }

    inner private class updateStudentAsyncTask(private val mAsyncTaskDAO: StudentDAO) : AsyncTask<String, Void, Void>() {

        override fun doInBackground(vararg name :String): Void? {
           /* var name = obj[0] as String
            var studentId = obj[1] as Int

            var classId1  = obj[2] as Int*/

          mAsyncTaskDAO.updateStudent(student_id =sId!!,classId = cId!!,name = updatedname!!  )
            return null
        }
    }

}