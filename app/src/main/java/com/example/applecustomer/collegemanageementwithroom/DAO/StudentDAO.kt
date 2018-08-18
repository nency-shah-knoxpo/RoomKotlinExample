package com.example.applecustomer.collegemanageementwithroom.DAO

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.support.annotation.NonNull
import com.example.applecustomer.collegemanageementwithroom.models.Student

@Dao
interface StudentDAO {

    @Insert
    fun addStudent(mStudent : Student)


    @Query("SELECT * From student_table where student_class_id = :classId ")
    fun getAllStudents(classId: Int): LiveData<List<Student>>

    @Query("Update student_table Set student_name = :name where student_id = :student_id and student_class_id = :classId")
    fun updateStudent(@NonNull student_id: Int,@NonNull classId : Int, @NonNull name: String)
}