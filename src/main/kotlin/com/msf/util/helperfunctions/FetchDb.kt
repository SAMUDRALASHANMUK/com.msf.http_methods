package com.msf.util.helperfunctions


import com.msf.database.table.Categories
import com.msf.database.table.Posts
import com.msf.database.table.Profiles
import com.msf.database.table.Users
import com.msf.database.table.PostCategories
import com.msf.model.Category
import com.msf.model.Post
import com.msf.model.Profile
import com.msf.model.User
import com.msf.model.PostCategory
import org.jetbrains.exposed.sql.ResultRow

fun resultRowToCategory(row: ResultRow) = Category(
    categoryId = row[Categories.id],
    categoryName = row[Categories.category_name]
)

fun resultRowToPost(row: ResultRow) = Post(
    postId = row[Posts.id],
    userId = row[Posts.userId],
    title = row[Posts.title],
    content = row[Posts.content]

)

fun resultRowToProfile(row: ResultRow) = Profile(
    profileId = row[Profiles.id],
    userId = row[Profiles.user_id],
    profileData = row[Profiles.profile_data],
)

fun resultRowToUser(row: ResultRow) = User(
    id = row[Users.id].toString(),
    userName = row[Users.user_name],
    email = row[Users.email],
)

fun resultPostCategory(row: ResultRow) = PostCategory(
    postId = row[PostCategories.postId],
    categoryId = row[PostCategories.categoryId]
)
