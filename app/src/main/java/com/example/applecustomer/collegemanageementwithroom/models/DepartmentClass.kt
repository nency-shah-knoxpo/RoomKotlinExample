package com.example.applecustomer.collegemanageementwithroom.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import android.support.annotation.NonNull
import java.util.*

@Entity(tableName = "class_table")

class DepartmentClass  {
    @ColumnInfo(name = "class_id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
     var mClassId : Int? = null


    @ColumnInfo(name = "class_name")
    var mClassName :String? = null

    @ColumnInfo(name = "class_desc")
    var mClassDesc : String? = null

    constructor(className : String,classDesc : String) {
        this.mClassName = className
        this.mClassDesc = classDesc

    }



}