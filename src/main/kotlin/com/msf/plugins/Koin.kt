package com.msf.plugins

import com.msf.data.repositories.UsersRepositoryImpl
import com.msf.data.repositories.ArticlesRepositoryImpl
import com.msf.data.repositories.PostRepositoryImpl
import com.msf.data.repositories.PostCategoriesRepositoryImpl
import com.msf.data.repositories.ProfileRepositoryImpl
import com.msf.data.repositories.UserLoginImpl
import com.msf.data.repositories.CategoryRepositoryImpl
import com.msf.data.repositories.MockUsersRepository
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {

    val myModule = module {
        single<UsersRepositoryImpl> { UsersRepositoryImpl() }
        single<ArticlesRepositoryImpl> { ArticlesRepositoryImpl() }
        single<PostRepositoryImpl> { PostRepositoryImpl() }
        single<PostCategoriesRepositoryImpl> { PostCategoriesRepositoryImpl() }
        single<ProfileRepositoryImpl> { ProfileRepositoryImpl() }
        single<UserLoginImpl> { UserLoginImpl() }
        single<CategoryRepositoryImpl> { CategoryRepositoryImpl() }
        single<MockUsersRepository> { MockUsersRepository() }
    }
    install(Koin) {
        modules(myModule)
    }
}