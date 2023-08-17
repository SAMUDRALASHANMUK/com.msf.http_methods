package com.msf.data.methods

import com.msf.data.model.*
import com.msf.data.schemas.*
import org.jetbrains.exposed.sql.ResultRow

fun resultRowToCategory(row: ResultRow) = Categorie(
    category_id = row[Categories.category_id],
    category_name = row[Categories.category_name]
)

fun resultRowToPost(row: ResultRow) = Post(
    post_id = row[Posts.post_id],
    user_id = row[Posts.user_id],
    title = row[Posts.title],
    content = row[Posts.content]

)

fun resultRowToProfile(row: ResultRow) = Profile(
    profile_id = row[Profiles.profile_id],
    user_id = row[Profiles.user_id],
    profile_data = row[Profiles.profile_data],
)

fun resultRowToUser(row: ResultRow) = User(
    user_id = row[Users.user_id],
    user_name = row[Users.user_name],
    email = row[Users.email],
)

fun resultPostCategory(row: ResultRow) = PostCategory(
    post_id = row[PostCategories.post_id],
    category_id = row[PostCategories.category_id]
)