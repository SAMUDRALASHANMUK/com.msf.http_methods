package com.msf.data.di


import com.msf.data.repositories.*
import com.msf.domain.interfaces.*
import org.koin.dsl.module

val myModule = module {
    single<UsersRepository> { UsersRepositoryImpl() }
    single<ArticlesRepository> { ArticlesRepositoryImpl() }
    single<PostRepository> { PostRepositoryImpl() }
    single<PostCategoriesRepository> { PostCategoriesRepositoryImpl() }
    single<ProfileRepository> { ProfileRepositoryImpl() }
    single<UserLoginImpl> { UserLoginImpl() }
    single<UsersRepository> { UsersRepositoryImpl() }
}