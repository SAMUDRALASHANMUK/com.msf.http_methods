package com.msf.data.methods

import com.msf.data.model.Category
import com.msf.data.model.Post
import com.msf.data.model.Profile
import com.msf.data.model.User
import com.msf.data.model.PostCategory
import com.msf.data.schemas.Categories
import com.msf.data.schemas.Posts
import com.msf.data.schemas.Profiles
import com.msf.data.schemas.Users
import com.msf.data.schemas.PostCategories
import org.jetbrains.exposed.sql.ResultRow

fun resultRowToCategory(row: ResultRow) = Category(
    categoryId = row[Categories.category_id],
    categoryName = row[Categories.category_name]
)

fun resultRowToPost(row: ResultRow) = Post(
    postId = row[Posts.post_id],
    userId = row[Posts.user_id],
    title = row[Posts.title],
    content = row[Posts.content]

)

fun resultRowToProfile(row: ResultRow) = Profile(
    profileId = row[Profiles.profile_id],
    userId = row[Profiles.user_id],
    profileData = row[Profiles.profile_data],
)

fun resultRowToUser(row: ResultRow) = User(
    userId = row[Users.user_id],
    userName = row[Users.user_name],
    email = row[Users.email],
)

fun resultPostCategory(row: ResultRow) = PostCategory(
    postId = row[PostCategories.post_id],
    categoryId = row[PostCategories.category_id]
)
