package com.msf.di

import com.msf.repository.*
import com.msf.services.*
import org.koin.dsl.module

val myModule = module {
    single<UsersRepositoryImpl> { UsersRepositoryImpl() }
    single<ArticlesRepositoryImpl> { ArticlesRepositoryImpl() }
    single<PostRepositoryImpl> { PostRepositoryImpl() }
    single<PostCategoriesRepositoryImpl> { PostCategoriesRepositoryImpl() }
    single<ProfileRepositoryImpl> { ProfileRepositoryImpl() }
    single<UserLoginImpl> { UserLoginImpl() }
    single<CategoryRepositoryImpl> { CategoryRepositoryImpl() }
    single<MockUsersRepository> { MockUsersRepository() }
    single<UserService> { UserService() }
    single<ProfileService> { ProfileService() }
    single<PostService> { PostService() }
    single<PostCategoryService> { PostCategoryService() }
    single<CategoryService> { CategoryService() }
    single<ArticleService> { ArticleService() }
}