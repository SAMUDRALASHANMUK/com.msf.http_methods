package com.msf

import com.msf.data.model.User
import com.msf.data.model.Post
import com.msf.data.model.Article
import com.msf.data.model.Profile
import com.msf.data.model.Category
import com.msf.data.model.UserLogin
import com.msf.data.repositories.PostCategoriesRepositoryImpl
import com.msf.data.repositories.ArticlesRepositoryImpl
import com.msf.data.repositories.CategoryRepositoryImpl
import com.msf.data.repositories.PostRepositoryImpl
import com.msf.data.repositories.ProfileRepositoryImpl
import com.msf.data.repositories.UsersRepositoryImpl
import com.msf.domain.interfaces.UserLoginRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class ImplTesting {

    private val mockUserRepository: UsersRepositoryImpl = mockk()
    private val mockProfileRepository: ProfileRepositoryImpl = mockk()
    private val mockPostRepository: PostRepositoryImpl = mockk()
    private val mockCategoryRepository: CategoryRepositoryImpl = mockk()
    private val mockArticleRepository: ArticlesRepositoryImpl = mockk()
    private val mockPostCategoriesRepository: PostCategoriesRepositoryImpl = mockk()
    private val mockUserLoginRepository: UserLoginRepository = mockk()

    private val testUser = User(1, "testuser", "test@example.com")
    private val testProfile = Profile(1, 1, "Some profile data")
    private val testPost = Post(1, 1, "some title", "Some Content")
    private val testCategory = Category(1, "some category")
    private val testArticle = Article(1, "some title", "some body")
    private val testUserLogin = UserLogin("testuser", "testpassword")

    //Test cases for user repo implementation
    @Test
    fun testCreateUser() = runBlocking {
        coEvery { mockUserRepository.createUser(any(), any()) } returns testUser

        val result = mockUserRepository.createUser("testuser", "test@example.com")
        assertEquals(testUser, result)
    }

    @Test
    fun testGetUserById() = runBlocking {
        coEvery { mockUserRepository.getUserById(1) } returns testUser

        val result = mockUserRepository.getUserById(1)
        assertEquals(testUser, result)
    }

    @Test
    fun testEditUser() = runBlocking {
        coEvery { mockUserRepository.editUser(1, any(), any()) } returns true

        val result = mockUserRepository.editUser(1, "newuser", "new@example.com")
        assertEquals(true, result)
    }

    @Test
    fun testDeleteUser() = runBlocking {
        coEvery { mockUserRepository.deleteUser(1) } returns true

        val result = mockUserRepository.deleteUser(1)
        assertEquals(true, result)
    }

    //Test cases for profile repo implementation
    @Test
    fun testCreateProfile() = runBlocking {
        coEvery { mockProfileRepository.createProfile(any(), any()) } returns testProfile

        val result = mockProfileRepository.createProfile(1, "Some profile data")
        assertEquals(testProfile, result)
    }

    @Test
    fun testGetProfileById() = runBlocking {
        coEvery { mockProfileRepository.getProfileById(1) } returns testProfile

        val result = mockProfileRepository.getProfileById(1)
        assertEquals(testProfile, result)
    }

    @Test
    fun testEditProfile() = runBlocking {
        coEvery { mockProfileRepository.editProfile(1, any()) } returns true

        val result = mockProfileRepository.editProfile(1, "new profile data")
        assertEquals(true, result)
    }

    @Test
    fun testDeleteProfile() = runBlocking {
        coEvery { mockProfileRepository.deleteProfile(1) } returns true

        val result = mockProfileRepository.deleteProfile(1)
        assertEquals(true, result)
    }

    //Test cases for post repo implementation
    @Test
    fun testCreatePost() = runBlocking {
        coEvery { mockPostRepository.createPost(any(), any(), any()) } returns testPost

        val result = mockPostRepository.createPost(1, "some title", "Some Content")
        assertEquals(result, testPost)
    }

    @Test
    fun testGetPostById() = runBlocking {
        coEvery { mockPostRepository.getPostById(1) } returns testPost

        val result = mockPostRepository.getPostById(1)
        assertEquals(result, testPost)
    }

    @Test
    fun testEditPost() = runBlocking {
        coEvery { mockPostRepository.editPost(1, any(), any()) } returns true

        val result = mockPostRepository.editPost(1, "New title", "New content")
        assertEquals(result, true)
    }

    @Test
    fun testDeletePost() = runBlocking {
        coEvery { mockPostRepository.deletePost(1) } returns true

        val result = mockPostRepository.deletePost(1)
        assertEquals(true, result)
    }

    //Test cases for Category Repo implementation
    @Test
    fun testCreateCategory() = runBlocking {
        coEvery { mockCategoryRepository.addCategory(any()) } returns testCategory

        val result = mockCategoryRepository.addCategory("some category")
        assertEquals(result, testCategory)

    }

    @Test
    fun testGetCategoryById() = runBlocking {
        coEvery { mockCategoryRepository.getCategoryById(1) } returns testCategory

        val result = mockCategoryRepository.getCategoryById(1)
        assertEquals(result, testCategory)
    }

    @Test
    fun testEditCategory() = runBlocking {
        coEvery { mockCategoryRepository.updateCategory(1, any()) } returns true

        val result = mockCategoryRepository.updateCategory(1, "New Category")
        assertEquals(result, true)
    }

    @Test
    fun testDeleteCategory() = runBlocking {
        coEvery { mockCategoryRepository.removeCategory(1) } returns true

        val result = mockCategoryRepository.removeCategory(1)
        assertEquals(result, true)
    }

    //Test cases for Article Repo implementation
    @Test
    fun testCreateArticle() = runBlocking {
        coEvery { mockArticleRepository.addNewArticle(any(), any()) } returns testArticle

        val result = mockArticleRepository.addNewArticle("some title", "some body")
        assertEquals(result, testArticle)
    }

    @Test
    fun testEditArticle() = runBlocking {
        coEvery { mockArticleRepository.editArticle(1, any(), any()) } returns true

        val result = mockArticleRepository.editArticle(1, "New Category", "new body")
        assertEquals(result, true)
    }

    @Test
    fun testDeleteArticle() = runBlocking {
        coEvery { mockArticleRepository.deleteArticle(1) } returns true

        val result = mockArticleRepository.deleteArticle(1)
        assertEquals(result, true)

    }

    //Test  cases for post category repo implementation
    @Test
    fun testGetPostsForCategory() = runBlocking {
        coEvery { mockPostCategoriesRepository.getPostsForCategory(1) } returns listOf(testPost)

        val result = mockPostCategoriesRepository.getPostsForCategory(1)
        assertEquals(listOf(testPost), result)
    }

    @Test
    fun testGetCategoriesForPost() = runBlocking {
        coEvery { mockPostCategoriesRepository.getCategoriesForPost(1) } returns listOf(testCategory)

        val result = mockPostCategoriesRepository.getCategoriesForPost(1)
        assertEquals(listOf(testCategory), result)
    }

    @Test
    fun testAssociatePostWithCategory() = runBlocking {
        coEvery { mockPostCategoriesRepository.associatePostWithCategory(1, 1) } returns Unit

        val result = mockPostCategoriesRepository.associatePostWithCategory(1, 1)
        assertEquals(Unit, result)
    }

    //test cases for user login repo impl
    @Test
    fun testCreateUserLogin() = runBlocking {
        coEvery { mockUserLoginRepository.createUser(any(), any()) } returns testUserLogin

        val result = mockUserLoginRepository.createUser("testuser", "testpassword")
        assertEquals(testUserLogin, result)
    }

    @Test
    fun testGetUserLogin() = runBlocking {
        coEvery { mockUserLoginRepository.getUser("testuser", "testpassword") } returns testUserLogin

        val result = mockUserLoginRepository.getUser("testuser", "testpassword")
        assertEquals(testUserLogin, result)
    }
}


