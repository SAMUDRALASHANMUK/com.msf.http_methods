import com.msf.data.repositories.PostCategoriesRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Application.configurePostCategoryRoutes() {

    val postCategoriesRepository: PostCategoriesRepositoryImpl by inject()

    routing {
        get("/categories/{category_id}/posts") {
            val categoryId = call.parameters["category_id"]?.toIntOrNull()

            if (categoryId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid category_id")
                return@get
            }

            val associatedPosts = postCategoriesRepository.getPostsForCategory(categoryId)
            call.respond(HttpStatusCode.OK, associatedPosts)
        }

        get("/posts/{post_id}/categories") {
            val postId = call.parameters["post_id"]?.toIntOrNull()

            if (postId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid post_id")
                return@get
            }

            val associatedCategories = postCategoriesRepository.getCategoriesForPost(postId)
            call.respond(HttpStatusCode.OK, associatedCategories)
        }
    }
}

