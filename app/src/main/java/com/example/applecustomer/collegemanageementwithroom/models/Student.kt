package com.example.applecustomer.collegemanageementwithroom.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull

@Entity(tableName = "student_table", foreignKeys = arrayOf(ForeignKey(entity = DepartmentClass::class, parentColumns = arrayOf("class_id"), childColumns = arrayOf("student_class_id"), onDelete = CASCADE)))
class Student() : Parcelable {


    @ColumnInfo(name = "student_class_id")
    @NonNull
    var mClassId: Int? = null


    @ColumnInfo(name = "student_id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    var mStudentId: Int? = null


    @ColumnInfo(name = "student_name")
    var mStudentName: String? = null

    constructor(parcel: Parcel) : this() {
        mClassId = parcel.readValue(Int::class.java.classLoader) as? Int
        mStudentId = parcel.readValue(Int::class.java.classLoader) as? Int
        mStudentName = parcel.readString()
    }

    constructor(studentName: String,classId : Int) : this() {
        this.mStudentName = studentName
        this.mClassId = classId
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(mClassId)
        parcel.writeValue(mStudentId)
        parcel.writeString(mStudentName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Student> {
        override fun createFromParcel(parcel: Parcel): Student {
            return Student(parcel)
        }

        override fun newArray(size: Int): Array<Student?> {
            return arrayOfNulls(size)
        }
    }

}