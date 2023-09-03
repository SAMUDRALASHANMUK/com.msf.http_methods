package com.msf.exception

class EmployeeNotFoundException : RuntimeException("Employee not found in the database")

class EmployeeCreateException : RuntimeException("Failed to create a new Employee")

class EmployeeUpdateException : RuntimeException("Failed to update the Employee information")

class EmployeeDeleteException : RuntimeException("Failed to delete the Employee")

