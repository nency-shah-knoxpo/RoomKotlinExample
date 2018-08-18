package com.example.applecustomer.collegemanageementwithroom

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.support.annotation.NonNull
import com.example.applecustomer.collegemanageementwithroom.Repository.StudentRepository
import com.example.applecustomer.collegemanageementwithroom.models.Student

class StudentViewModel(@NonNull application: Application) : AndroidViewModel(application) {

    private var mStudentRepository: StudentRepository = StudentRepository(application)

    fun insertStudent(student: Student) {

        mStudentRepository.addStudent(student)
    }

    fun getAllStudents(classId: Int): LiveData<List<Student>> {
        return  mStudentRepository.getAllStudent(classId)

    }

    fun updateStudent(studentId : Int , classId : Int , name : String){
mStudentRepository.updateStudent(studentId,classId,name)
    }
}

