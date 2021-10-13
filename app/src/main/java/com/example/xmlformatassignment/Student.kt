package com.example.xmlformatassignment

class Student{
    var id: String? = null
    var name: String? = null
    var marks: Float = 0f

    override fun toString(): String {
        return " Id = $id \n Name = $name \n Salary = $marks"
    }
}