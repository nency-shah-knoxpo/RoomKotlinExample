package com.example.applecustomer.collegemanageementwithroom.DAO

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.applecustomer.collegemanageementwithroom.models.DepartmentClass

@Dao
interface ClassDAO {

    @Insert
    fun addClass(mClass : DepartmentClass)


    @Query("SELECT * From class_table")
    fun getAllClasses() : LiveData<List<DepartmentClass>>

    }

