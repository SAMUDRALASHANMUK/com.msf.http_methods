package com.msf

import com.google.gson.JsonParser
import com.msf.model.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals


class ApplicationTest {


    private val testUser = User(2, "Jet", "Brains@gmail.com")
    private val testProfile = Profile(1, 10, "Some profile data")
    private val testPost = Post(1, 1, "some title", "Some Content")
    private val testCategory = Category(1, "some category")
    private val testArticle = Article(1, "some title", "some body")
    private val testUserLogin = UserLogin("testuser", "testpassword")


    //Test cases for user routes
    @Test
    fun `test POST should add a new user`() = testApplication {

        val serializedUser = Json.encodeToString(testUser)
        val response = client.post("/users") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedUser)
        }
        assertEquals(HttpStatusCode.Created, response.status)
        val responseUser = Json.decodeFromString<User>(response.bodyAsText())
        assertEquals(testUser, responseUser)

    }

    @Test
    fun `test simple GET should return list of users`() = testApplication {
        val expectedJson = """
         [
         {
           "userId": 1,
           "userName": "Jet",
           "email": "Brains@gmail.com"
         }
         ]
    """.trimIndent()
        val response = client.get("/users") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        val responseJson = JsonParser.parseString(response.bodyAsText())
        val expectedJsonParsed = JsonParser.parseString(expectedJson)
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(expectedJsonParsed, responseJson)
    }


    @Test
    fun `test GET  should return user based on id`() = testApplication {

        val response = client.get("/users/1") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        assertEquals(HttpStatusCode.OK, response.status)
        val responseUser = Json.decodeFromString<User>(response.bodyAsText())
        assertEquals(testUser, responseUser)
    }

    @Test
    fun `test PUT should update the user`() = testApplication {

        val updatedUser = User(1, "UpdatedJohn", "updatedjohn@example.com")
        val serializedUser = Json.encodeToString(updatedUser)
        val response = client.put("users/1") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedUser)
        }
        assertEquals(HttpStatusCode.OK, response.status)

    }

    @Test
    fun `test DELETE should remove the user`() = testApplication {

        val response = client.delete("/users/2") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        assertEquals(HttpStatusCode.OK, response.status)

    }


    // Test cases for profile routes


    @Test
    fun `test POST should add a new profile`() = testApplication {

        val serializedProfile = Json.encodeToString(testProfile)
        val response = client.post("/profiles") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedProfile)
        }
        assertEquals(HttpStatusCode.Created, response.status)
        val responseProfile = Json.decodeFromString<Profile>(response.bodyAsText())
        assertEquals(testProfile, responseProfile)
    }

    @Test
    fun `test GET all profiles should return list of profiles`() = testApplication {
        val expectedJson = """
         [
         {
           "profileId": 1,
           "userId": 1,
           "profileData": "Some profile data"
         }
         ]
    """.trimIndent()
        val response = client.get("/profiles") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        val responseJson = JsonParser.parseString(response.bodyAsText())
        val expectedJsonParsed = JsonParser.parseString(expectedJson)
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(expectedJsonParsed, responseJson)
    }

    @Test
    fun `test GET profile by ID should return the correct profile`() = testApplication {

        val response = client.get("/profiles/1") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        assertEquals(HttpStatusCode.OK, response.status)
        val responseProfile = Json.decodeFromString<Profile>(response.bodyAsText())
        assertEquals(testProfile, responseProfile)
    }

    @Test
    fun `test PUT should update the profile`() = testApplication {

        val updatedProfile = Profile(1, 1, "This is profile data")
        val serializedProfile = Json.encodeToString(updatedProfile)
        val response = client.put("/profiles/1") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedProfile)
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `test DELETE should remove the profile`() = testApplication {

        val response = client.delete("/profiles/1") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }


//    //Test cases for post routes
//    @Test
//    fun `test GET all posts should return list of posts`() = testApplication {
//
//        val response = client.get("/posts/") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//        }
//        assertEquals(HttpStatusCode.OK, response.status)
//    }
//
//    @Test
//    fun `test POST should add a new posts`() = testApplication {
//        val postUser = Post(10, 14, "some title", "some content")
//        val serializedPost = Json.encodeToString(postUser)
//        val response = client.post("/posts/") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//            setBody(serializedPost)
//        }
//        val responsePost = Json.decodeFromString<Post>(response.bodyAsText())
//        assertEquals(HttpStatusCode.Created, response.status)
//        //assertEquals(postUser, responsePost)
//        assertEquals(postUser.copy(postId = responsePost.postId), responsePost)
//    }
//
//    @Test
//    fun `test GET post by ID should return the correct post`() = testApplication {
//        val post = Post(1, 14, "some title", "some content")
//        val response = client.get("posts/1") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//        }
//        val deserializedPost = Json.decodeFromString<Post>(response.bodyAsText())
//        assertEquals(HttpStatusCode.OK, response.status)
//        assertEquals(post, deserializedPost)
//    }
//
//    @Test
//    fun `test DELETE should return BadRequest for invalid post ID`() = testApplication {
//
//        val response = client.delete("/posts/123") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//        }
//        assertEquals(HttpStatusCode.BadRequest, response.status)
//    }
//
//    @Test
//    fun `test PUT should update the post`() = testApplication {
//
//        val post = Post(8, 2, "some title", "some content")
//
//        val updatedPost = Post(8, 2, "updated title", "some content")
//        val serializedPost = Json.encodeToString(updatedPost)
//        val response = client.put("/posts/1") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//            setBody(serializedPost)
//        }
//        assertEquals(HttpStatusCode.OK, response.status)
//    }
//
//    //employee routes
//    @Test
//    fun `test POST should add a new employee`() = testApplication {
//
//        val employe1 = Employee(1, "shanmuk", 21, "shanmuk2017@gmail.com")
//        val serializedEmployee = Json.encodeToString(employe1)
//        val response = client.post("/employee/details") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//            setBody(serializedEmployee)
//        }
//        assertEquals(HttpStatusCode.OK, response.status)
//        val deserializedEmployee = Json.decodeFromString<Employee>(response.bodyAsText())
//        //assertEquals(deserializedEmployee, employe1)
//        assertEquals(employe1.copy(id = deserializedEmployee.id), deserializedEmployee)
//    }
//
//    @Test
//    fun `test GET all should return list of employees`() = testApplication {
//
//        val employe1 = Employee(1, "shanmuk", 21, "shanmuk2017@gmail.com")
//        val response = client.get("/") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//            setBody(employe1)
//        }
//        assertEquals(HttpStatusCode.OK, response.status)
//        val userList = Json.decodeFromString<List<Employee>>(response.bodyAsText())
//        assertEquals(Employee.empList, userList)
//    }
//
//    @Test
//    fun `test GET post by ID should return the correct employee`() = testApplication {
//        val employe1 = Employee(1, "shanmuk", 21, "shanmuk2017@gmail.com")
//        Employee.empList.add(employe1)
//        val updatedEmp = Employee(1, "updated name", 21, "shanmuk2017@gmail.com")
//        val response = client.get("posts/7") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//            setBody(updatedEmp)
//        }
//        val deserializedEmp = Json.decodeFromString<Employee>(response.bodyAsText())
//        assertEquals(HttpStatusCode.OK, response.status)
//        assertEquals(updatedEmp, deserializedEmp)
//    }
//
//    @Test
//    fun `test DELETE should remove the employee`() = testApplication {
//        application {
//            module()
//        }
//        val employee = Employee(3, "John", 22, "john@example.com")
//        Employee.empList.add(employee)
//        val response = client.delete("/") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//        }
//        assertEquals(HttpStatusCode.OK, response.status)
//
//    }
//
//    @Test
//    fun `test PUT should update the employee`() = testApplication {
//
//        val employee = Employee(3, "John", 22, "john@example.com")
//        Employee.empList.add(employee)
//        val updatedEmp = Employee(3, "new name", 22, "john@example.com")
//        val serializedUser = Json.encodeToString(updatedEmp)
//        val response = client.put("users/1") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//            setBody(serializedUser)
//        }
//        assertEquals(HttpStatusCode.OK, response.status)
//        // val updatedEmpInList = list.empList.find { it.id == updatedEmp.id }
//        // val updatedEmpInList = Json.decodeFromString<Employee>(response.bodyAsText())
//        // assertEquals(updatedEmp, updatedEmpInList)
//    }
//
//    @Test
//    fun `test PATCH should update the employee`() = testApplication {
//
//        val employee = Employee(3, "John", 22, "john@example.com")
//        Employee.empList.add(employee)
//
//        val updatedFields = mapOf(
//            "name" to "new name",
//        )
//
//        val serializedFields = Json.encodeToString(updatedFields)
//
//        val response = client.patch("users/${employee.id}") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//            setBody(serializedFields)
//        }
//
//        assertEquals(HttpStatusCode.OK, response.status)
//
//        val updatedEmpInList = Employee.empList.find { it.id == employee.id }
//
//        updatedEmpInList!!.apply {
//            name = updatedFields["name"] as String
//        }
//
//        assertEquals(updatedFields["name"], updatedEmpInList.name)
//    }
//
//    //Test cases for category routes
//    @Test
//    fun `test POST should add a new category`() = testApplication {
//
//        val category = Category(8, "shanmuk1")
//        val serializedCategory = Json.encodeToString(category)
//        val response = client.post("/categories") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//            setBody(serializedCategory)
//        }
//        assertEquals(HttpStatusCode.Created, response.status)
//        val deserializedCategory = Json.decodeFromString<Category>(response.bodyAsText())
//        //assertEquals(deserializedCategory, category)
//        assertEquals(category.copy(categoryId = deserializedCategory.categoryId), deserializedCategory)
//    }
//
//    @Test
//    fun `test GET all should return list of categorie`() = testApplication {
//
//        val response = client.get("/categories") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//        }
//        assertEquals(HttpStatusCode.OK, response.status)
//
//    }
//
//    @Test
//    fun `test GET Categorie by ID should return the correct categorie`() = testApplication {
//        val category = Category(1, "shanmuk1")
//        val response = client.get("/categories/1") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//        }
//        val deserializedCategory = Json.decodeFromString<Category>(response.bodyAsText())
//        assertEquals(HttpStatusCode.OK, response.status)
//        assertEquals(category, deserializedCategory)
//    }
//
//    @Test
//    fun `test DELETE should return Not Found for a non-existing category`() = testApplication {
//
//        val categoryId = 9999
//
//        val response = client.delete("/categories/$categoryId") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//        }
//
//        assertEquals(HttpStatusCode.OK, response.status)
//    }
//
//
//    @Test
//    fun `test PUT should update the categorie`() = testApplication {
//
//        val updatedCategory = Category(7, "updated name")
//        val serializedCategory = Json.encodeToString(updatedCategory)
//        val categoryId = 7
//        val response = client.put("categories/$categoryId") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//            setBody(serializedCategory)
//        }
//        assertEquals(HttpStatusCode.NotFound, response.status)
//
//    }
//
//    //test cases for article routes
//    @Test
//    fun `test GET Article by ID should return the correct Article`() = testApplication {
//        val article = Article(9, "article title", "article body")
//        val response = client.get("/articles/9") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//        }
//        val serializedArticle = Json.encodeToString(article)
//        assertEquals(HttpStatusCode.OK, response.status)
//        //assertEquals(response.bodyAsText() ,serializedArticle)
//    }
//
//    @Test
//    fun `test GET all Articles should return the list of Articles`() = testApplication {
//        val response = client.get("/articles/") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//        }
//        assertEquals(HttpStatusCode.OK, response.status)
//
//    }
//
//    @Test
//    fun `test POST should add a new Article`() = testApplication {
//        val article = Article(12, "title", "body")
//        val serializedArticle = Json.encodeToString(article)
//        val response = client.post("/articles/") {
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//            setBody(serializedArticle)
//        }
//        val deserializedArticle = Json.decodeFromString<Article>(response.bodyAsText())
//        assertEquals(HttpStatusCode.Created, response.status)
//        // assertEquals(article, deserializedArticle)
//        assertEquals(article.copy(id = deserializedArticle.id), deserializedArticle)
//    }
//
//    //Test cases for post category routes
//    @Test
//    fun `test GET Posts for Category should return the correct Posts`() = testApplication {
////        val expectedPosts = listOf(
////            Post(3, 2, "Some profile data", "some content"),
////            Post(4, 2, "Some profile data", "some content"),
////            Post(5, 2, "Some profile data", "some content"),
////            Post(6, 2, "some title", "some content"),
////            Post(7, 2, "some title", "some content"),
////            Post(8, 2, "some title", "some content")
////        )
//        val response = client.get("/categories/2/posts")
//        assertEquals(HttpStatusCode.OK, response.status)
//        // val responsePosts = Json.decodeFromString<List<Post>>(response.bodyAsText())
//        //assertEquals(expectedPosts, responsePosts)
//    }
//
//
//    @Test
//    fun `test GET Categories for Post should return the correct Categories`() = testApplication {
////        val expectedCategories = listOf(Categorie(5, "happy1"), Categorie(6, "happy2"), Categorie(7, "shanmuk")
////        )
//        val response = client.get("/posts/2/categories")
//        assertEquals(HttpStatusCode.OK, response.status)
//        //val responseCategories = Json.decodeFromString<List<Categorie>>(response.bodyAsText())
//        // assertEquals(expectedCategories, responseCategories)
//    }
//
//    //File download routes
//    @Test
//    fun `testDownloadRouteWithValidUrl`() = testApplication {
//
//        val url = "https://www.africau.edu/images/default/sample.pdf"
//        val response = client.get("/download?url=$url")
//        assertEquals(HttpStatusCode.OK, response.status)
//        assertEquals("File downloaded and saved to sample.pdf", response.bodyAsText().trim('"'))
//    }
//
//    @Test
//    fun `testDownloadRouteWithInvalidUrl`() = testApplication {
//
//        val response = client.get("/download")
//        assertEquals(HttpStatusCode.BadRequest, response.status)
//        assertEquals("Error: Please provide a valid file URL.", response.bodyAsText().trim('"'))
//    }
//
//
//    //Test cases for user login routes
//    @Test
//    fun `testLoginRoute`() = testApplication {
//
//        val response = client.post("/loginuser") {
//            setBody("{\"userName\": \"testuser\", \"password\": \"password123\"}")
//            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
//        }
//
//        val responseContent = response.bodyAsText()
//        assertEquals(HttpStatusCode.OK, response.status)
//        assertNotNull(responseContent)
//        assertTrue(responseContent!!.contains("token"))
//
//    }
//
//    @Test
//    fun `testHelloRouteWithValidToken`() = testApplication {
//        val token = generateValidToken("testuser")
//
//        val response = client.get("/userlogin/hello") {
//            headers[HttpHeaders.Authorization] = "Bearer $token"
//        }
//        val expectedResponse = "Hello, testuser! Token is expired at"
//
//        assertEquals(HttpStatusCode.OK, response.status)
//        assertTrue(response.bodyAsText().startsWith(expectedResponse))
//    }
//
//    @Test
//    fun `testHelloRouteWithoutToken`() = testApplication {
//        val response = client.get("/userlogin/hello")
//        assertEquals(HttpStatusCode.Unauthorized, response.status)
//    }
//
//    private fun generateValidToken(username: String): String {
//        val config = HoconApplicationConfig(ConfigFactory.load())
//        val secret = config.property("ktor.jwt.secret").getString()
//        val issuer = config.property("ktor.jwt.issuer").getString()
//        val audience = config.property("ktor.jwt.audience").getString()
//        return JWT.create().withAudience(audience).withIssuer(issuer).withClaim("username", username)
//            .withExpiresAt(Date(System.currentTimeMillis() + 60000)).sign(Algorithm.HMAC256(secret))
//    }
//
//    //Test cases for user session routes
//    @Test
//    fun `test login route`() = testApplication {
//
//        val response = client.get("/login")
//        assertEquals(HttpStatusCode.OK, response.status)
//        assertEquals("/user", response.headers["Location"])
//    }
//
//    @Test
//    fun `test user route with session`() = testApplication {
//
//        val response = client.get("/user")
//        assertEquals(HttpStatusCode.OK, response.status)
//    }
//
//    @Test
//    fun `test user route without session`() = testApplication {
//        val response = client.get("/user")
//        assertEquals(HttpStatusCode.OK, response.status)
//        assertEquals("Not logged in", response.bodyAsText().trim('"'))
//    }
//
//    @Test
//    fun `test logout route with session`() = testApplication {
//
//        val response = client.get("/logout")
//        assertEquals(HttpStatusCode.OK, response.status)
//        assertEquals("/user", response.headers["Location"])
//    }
//
//    @Test
//    fun `test logout route without session`() = testApplication {
//
//        val response = client.get("/logout")
//        assertEquals(HttpStatusCode.OK, response.status)
//        assertEquals("/user", response.headers["Location"])
//    }
//
//    //Test cases for post client test
//    @Test
//    fun `test for Post Client Test Route`() = testApplication {
//        val response = client.get("/postClientTest")
//        assertEquals(HttpStatusCode.OK, response.status)
//
//    }
}















