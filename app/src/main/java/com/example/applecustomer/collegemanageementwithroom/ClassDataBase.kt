package com.example.applecustomer.collegemanageementwithroom

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.applecustomer.collegemanageementwithroom.DAO.ClassDAO
import com.example.applecustomer.collegemanageementwithroom.DAO.StudentDAO
import com.example.applecustomer.collegemanageementwithroom.models.DepartmentClass
import com.example.applecustomer.collegemanageementwithroom.models.Student

@Database(entities = [DepartmentClass::class, Student::class],version = 2)
abstract class ClassDataBase : RoomDatabase() {
    abstract fun classDAO(): ClassDAO
    abstract fun studentDAO(): StudentDAO


    companion object {

        private var INSTANCE: ClassDataBase? = null

        fun getClassDataBase(context: Context): ClassDataBase? {

            if (INSTANCE == null) {
                synchronized(ClassDataBase::class.java)
                {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, ClassDataBase::class.java, "class_database").fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

    }
}

