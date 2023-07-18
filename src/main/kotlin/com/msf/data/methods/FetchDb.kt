package com.msf.data.methods

import com.msf.data.model.Categorie
import com.msf.data.model.Post
import com.msf.data.model.Profile
import com.msf.data.model.User
import com.msf.data.schemas.Categories
import com.msf.data.schemas.Posts
import com.msf.data.schemas.Profiles
import com.msf.data.schemas.Users
import org.jetbrains.exposed.sql.ResultRow

fun resultRowToCategorie(row: ResultRow) = Categorie(
    category_id = row[Categories.category_id],
    category_name = row[Categories.category_name],
    post_id = row[Categories.post_id]
)

fun resultRowToPost(row: ResultRow) = Post(
    post_id = row[Posts.post_id],
    user_id = row[Posts.user_id],
    title = row[Posts.title],
    content = row[Posts.content],
    category_id = row[Posts.category_id]
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