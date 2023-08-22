package com.msf.routes

import com.msf.data.methods.getData
import com.msf.data.model.Employee
import com.msf.domain.exceptions.EmployeeNotFoundException
import com.msf.util.appconstants.ApiEndPoints.EMPLOYEE
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.*

fun Application.configureEmpRoutes() {

    routing {
        route(EMPLOYEE) {
            //getting all the employee details
            get("/") {
                if (Employee.empList.isEmpty()) {
                    call.respondText("employee not found", status = HttpStatusCode.BadRequest)
                } else {
                    call.respond(Employee.empList)
                }
            }
            //getting the employee details by using id
            get("/{id?}") {
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "No parameters",
                    status = HttpStatusCode.BadRequest
                )
                val employee = Employee.empList.find { it.id == id.toInt() }
                if (employee != null) {
                    call.respond(employee)
                } else {
                    throw EmployeeNotFoundException("Employee not found with ID: $id")
                }
            }
            get("/internal-error") {
                throw InternalError("Internal Server Error")
            }
            get("/api/data") {
                call.respond(getData())
            }

            //adding the employee
            post("/employee/details") {
                val employee = call.receive<Employee>()
                val employeeExists = Employee.empList.any {
                    it.id == employee.id
                }
                if (employeeExists) {
                    call.respondText("Employee ID already exists", status = HttpStatusCode.BadRequest)
                } else {
                    Employee.empList.add(employee)
                    call.respond(employee)
                }
            }


            //updating the emp details
            put("/") {
                val employeePostman = call.receive<Employee>()
                val employee = Employee.empList.find {
                    it.id == employeePostman.id
                } ?: return@put call.respondText("unable to add because id not found")

                Employee.empList[Employee.empList.indexOf(employee)] = employeePostman
                call.respondText("employee updated")
            }

            //delete employee by using id
            delete("/{id?}") {
                val id = call.parameters["id"]?.toInt()
                val employee = Employee.empList.find {
                    it.id == id
                } ?: return@delete call.respondText("Employee not found with the given id")
                Employee.empList.remove(employee)
                call.respondText("Employee deleted", status = HttpStatusCode.OK)
            }


            //updating the specific value by using id
            patch("/{id?}") {
                val id = call.parameters["id"]?.toInt()
                val name = call.receive<Map<String, String>>()["name"]
                val employee = Employee.empList.find {
                    it.id == id
                } ?: return@patch call.respondText("Employee not found with the given id")
                val index = Employee.empList.indexOf(employee)

                // Update the specific value (name) for the employee
                if (name != null) {
                    employee.name = name
                }
                Employee.empList[index] = employee

                call.respondText("Name updated for employee with ID: $id", status = HttpStatusCode.OK)
            }
        }
    }
}

