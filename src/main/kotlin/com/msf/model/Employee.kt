package com.msf.model

import com.msf.util.appconstants.GlobalConstants.RANDOM_NUMBER_MAX
import com.msf.util.appconstants.GlobalConstants.RANDOM_NUMBER_MIN
import kotlinx.serialization.Serializable
import java.util.Random

@Serializable
data class Employee(val id: Int = generateRandomId(), var name: String, val age: Int, val email: String) {
    companion object {
        var empList = mutableListOf<Employee>()
    }
}

private fun generateRandomId(): Int {
    return Random().nextInt(RANDOM_NUMBER_MIN, RANDOM_NUMBER_MAX)
}

