package com.msf.data.model

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Employee(val id: String = generateRandomId().toString(), var name: String, val age: Int, val email: String)

private fun generateRandomId(): Int {
    return Random().nextInt(1,100)
}

var empList = mutableListOf<Employee>()