package com.msf.plugins

import com.msf.data.repositories.*
import io.ktor.server.application.*
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
    }
    install(Koin) {
        modules(myModule)
    }

}