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
                employeeService.getAllEmployees().apply {
                    call.respond(this)
                }
            }


            get(GET_EMPLOYEE) {
                val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText(
                    "No parameters",
                    status = HttpStatusCode.BadRequest
                )
                employeeService.getEmployeeById(id).apply { call.respond(this) }

            }
            get(CLIENT_API) {
                getData().apply { call.respond(this) }
            }

            post {
                val employee = call.receive<Employee>()
                employeeService.createEmployee(employee).apply { call.respond(HttpStatusCode.Created, this) }
            }

            put {
                val employeePostman = call.receive<Employee>()
                employeeService.updateEmployee(employeePostman).apply { call.respond(this) }
            }

            delete(DELETE_EMPLOYEE) {
                val id = call.parameters["id"]?.toInt() ?: return@delete call.respond(
                    HttpStatusCode.BadRequest,
                    "please provide employee id"
                )
                employeeService.deleteEmployee(id).apply { call.respond(this, "Employee deleted") }
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
                employeeService.editEmployee(id, name).apply { call.respond(this, "Name updated for employee") }
            }
        }
    }
}

