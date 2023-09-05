import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

@Serializable
data class Article(@Contextual val id: EntityID<@Contextual UUID>, val title: String, val body: String)
