package com.msf.config.status

class PostCategoryCreateException : RuntimeException("unable to create post category")
class PostCategoryException : RuntimeException("Category/Post not found in the database")
