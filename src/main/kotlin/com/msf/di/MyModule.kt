package com.msf.di

import com.msf.dao.*
import com.msf.repository.*
import com.msf.services.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val myModule = module {

    singleOf(::ArticlesRepository) { bind<ArticleDAO>() }
    singleOf(::CategoryRepository) { bind<CategoryDAO>() }
    singleOf(::PostCategoriesRepository) { bind<PostCategoryDAO>() }
    singleOf(::PostRepository) { bind<PostDAO>() }
    singleOf(::ProfileRepository) { bind<ProfileDAO>() }
    singleOf(::UsersRepository) { bind<UserDAO>() }
    singleOf(::UserLoginRepository) { bind<UserLoginDAO>() }
    singleOf(::ArticleService)
    singleOf(::CategoryService)
    singleOf(::EmployeeService)
    singleOf(::PostCategoryService)
    singleOf(::PostService)
    singleOf(::ProfileService)
    singleOf(::UserService)

}
