package com.msf.model

import kotlinx.serialization.Serializable

@Serializable
data class Employee(val id: String, var name: String, val age: Int, val email: String)

var empList = mutableListOf<Employee>()