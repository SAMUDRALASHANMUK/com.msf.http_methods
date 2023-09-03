package com.msf.exception

class CategoryNotFoundException : RuntimeException("Category not found in the database")

class CategoryCreateException : RuntimeException("Failed to create a new Category")

class CategoryUpdateException : RuntimeException("Failed to update the Category information")

class CategoryDeleteException : RuntimeException("Failed to delete the Category")

