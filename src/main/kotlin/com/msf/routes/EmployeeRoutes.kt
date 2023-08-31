package com.msf.routes

import com.msf.model.Employee
import com.msf.services.EmployeeService
import com.msf.util.appconstants.ApiEndPoints.CLIENT_API
import com.msf.util.appconstants.ApiEndPoints.DELETE_EMPLOYEE
import com.msf.util.appconstants.ApiEndPoints.EDIT_EMPLOYEE_NAME
import com.msf.util.appconstants.ApiEndPoints.EMPLOYEE
import com.msf.util.appconstants.ApiEndPoints.GET_EMPLOYEE
import com.msf.util.helperfunctions.getData
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureEmpRoutes() {

    routing {
        route(EMPLOYEE) {
            val employeeService: EmployeeService by inject()

            get {
                val response = employeeService.getAllEmployees()
                call.respond(response)
            }


            get(GET_EMPLOYEE) {
                val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText(
                    "No parameters",
                    status = HttpStatusCode.BadRequest
                )
                val response = employeeService.getEmployeeById(id)
                call.respond(response)

            }
            get(CLIENT_API) {
                call.respond(getData())
            }

            post {
                val employee = call.receive<Employee>()
                val response = employeeService.createEmployee(employee)
                call.respond(HttpStatusCode.Created, response)
            }

            put {
                val employeePostman = call.receive<Employee>()
                val response = employeeService.updateEmployee(employeePostman)
                call.respond(response)
            }

            delete(DELETE_EMPLOYEE) {
                val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(
                    HttpStatusCode.BadRequest,
                    "please provide employee id"
                )
                val response = employeeService.deleteEmployee(id)
                call.respond(response, "Employee deleted")
            }

            patch(EDIT_EMPLOYEE_NAME) {
                val id = call.parameters["id"]?.toInt() ?: return@patch call.respond(
                    HttpStatusCode.BadRequest,
                    "Please provide employee id"
                )
                val name = call.receive<Map<String, String>>()["name"] ?: return@patch call.respond(
                    HttpStatusCode.BadRequest,
                    "please provide name"
                )
                val response = employeeService.editEmployee(id, name)
                call.respond(response, "Name updated for employee")
            }
        }
    }
}

