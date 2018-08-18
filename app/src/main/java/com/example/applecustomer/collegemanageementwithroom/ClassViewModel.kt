package com.example.applecustomer.collegemanageementwithroom

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.support.annotation.NonNull
import com.example.applecustomer.collegemanageementwithroom.Repository.ClassRepository
import com.example.applecustomer.collegemanageementwithroom.Repository.StudentRepository
import com.example.applecustomer.collegemanageementwithroom.models.DepartmentClass
import com.example.applecustomer.collegemanageementwithroom.models.Student

class ClassViewModel(@NonNull application: Application) : AndroidViewModel(application) {

    private  var  mRepository : ClassRepository = ClassRepository(application)
    private  var  mAllClasses: LiveData<List<DepartmentClass>>

    init {

        this.mAllClasses = mRepository.getAllClasses()
    }

    fun insert(department: DepartmentClass) {
        mRepository.addClass(department)
    }



    fun getAllClasses() : LiveData<List<DepartmentClass>>{

    return mAllClasses
}






}