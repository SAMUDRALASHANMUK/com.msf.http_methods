package com.msf.routes

import com.msf.model.Employee
import com.msf.model.empList
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.empRoutes() {
    routing {

        //getting all the employee details
        get("/") {
            if (empList.isEmpty()) {
                call.respondText("employee not found", status = HttpStatusCode.BadRequest)
            } else {
                call.respond(empList)
            }
        }

        //getting the employee details by using id
        get("/{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText("No paramters", status = HttpStatusCode.OK)
            val employee = empList.find {
                it.id == id
            } ?: return@get call.respondText("Employee not found", status = HttpStatusCode.BadRequest)

            return@get call.respond(employee)
        }

        //adding the employee
        post("/employee/details") {
            val employee = call.receive<Employee>()
            val employeeExists = empList.any {
                it.id == employee.id
            }
            if (employeeExists) {
                call.respondText("Employee ID already exists", status = HttpStatusCode.BadRequest)
            } else {
                empList.add(employee)
                call.respondText("Employee added", status = HttpStatusCode.OK)
            }
        }


        //updating the emp details
        put("/") {
            val employeePostman = call.receive<Employee>()
            val employee = empList.find {
                it.id == employeePostman.id
            } ?: return@put call.respondText("unable to add because id not found")

            empList.set(empList.indexOf(employee), employeePostman)
            call.respondText("employee updated")
        }

        //delete employee by using id
        delete("/{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "No parameters",
                status = HttpStatusCode.BadRequest
            )
            val employee = empList.find {
                it.id == id
            } ?: return@delete call.respondText("Employee not found with the given id")
            empList.remove(employee)
            call.respondText("Employee deleted", status = HttpStatusCode.OK)
        }


        //updating the specific value by using id
        patch("/{id?}") {
            val id = call.parameters["id"] ?: return@patch call.respondText(
                "No parameters",
                status = HttpStatusCode.BadRequest
            )
            val name = call.receiveText()

            val employee = empList.find {
                it.id == id
            } ?: return@patch call.respondText("Employee not found with the given id")

            // Update the specific value (name) for the employee
            employee.name = name

            call.respondText("Name updated for employee with ID: $id", status = HttpStatusCode.OK)
        }


    }
}