package com.msf.exception

class ProfileNotFoundException : RuntimeException("Profile not found in the database")

class ProfileCreateException : RuntimeException("Failed to create a new profile")

class ProfileUpdateException : RuntimeException("Failed to update the profile information")

class ProfileDeleteException : RuntimeException("Failed to delete the profile")
