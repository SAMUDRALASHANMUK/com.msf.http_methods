package com.msf.exception

class PostNotFoundException : RuntimeException("Post not found in the database")

class PostCreateException : RuntimeException("Failed to create a new post")

class PostUpdateException : RuntimeException("Failed to update the post")

class PostDeleteException : RuntimeException("Failed to delete the post")
