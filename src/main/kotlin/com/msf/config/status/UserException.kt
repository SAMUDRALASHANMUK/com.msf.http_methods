package com.msf.config.status

class UserNotFoundException : RuntimeException("User not found in the database")

class UserCreateException : RuntimeException("Failed to create a new user")

class UserUpdateException : RuntimeException("Failed to update the user information")

class UserDeleteException : RuntimeException("Failed to delete the user")
