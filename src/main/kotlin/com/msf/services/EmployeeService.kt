package com.msf.services

import com.msf.config.status.EmployeeCreateException
import com.msf.config.status.EmployeeDeleteException
import com.msf.config.status.EmployeeNotFoundException
import com.msf.config.status.EmployeeUpdateException
import com.msf.model.Employee
import io.ktor.http.*


class EmployeeService {
    fun getAllEmployees(): List<Employee> {
        if (Employee.empList.isEmpty()) {
            throw EmployeeNotFoundException()
        } else {
            return Employee.empList
        }
    }

    fun getEmployeeById(id: Int): Employee {
        val employee = Employee.empList.find { it.id == id }
        return employee ?: throw EmployeeNotFoundException()
    }

    fun createEmployee(employee: Employee): Employee {
        val employeeExists = Employee.empList.any {
            it.id == employee.id
        }
        if (employeeExists) {
            throw EmployeeCreateException()
        } else {
            Employee.empList.add(employee)
            return employee
        }
    }

    fun deleteEmployee(id: Int): HttpStatusCode {
        val employee = Employee.empList.find {
            it.id == id
        }
        if (employee != null) {
            Employee.empList.remove(employee)
            return HttpStatusCode.OK
        } else {
            throw EmployeeDeleteException()
        }
    }

    fun editEmployee(id: Int, name: String): HttpStatusCode {
        val employee = Employee.empList.find {
            it.id == id
        }
        if (employee != null) {
            val index = Employee.empList.indexOf(employee)
            employee.name = name
            Employee.empList[index] = employee
            return HttpStatusCode.OK
        } else {
            throw EmployeeUpdateException()
        }
    }

    fun updateEmployee(emp: Employee): HttpStatusCode {
        val employee = Employee.empList.find {
            it.id == emp.id
        }
        if (employee != null) {
            Employee.empList[Employee.empList.indexOf(employee)] = employee
            return HttpStatusCode.OK
        } else {
            throw EmployeeUpdateException()
        }
    }
}