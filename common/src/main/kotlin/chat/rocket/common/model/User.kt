package chat.rocket.common.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class User(
    @Json(name = "_id") val id: String,
    override val username: String?,
    override val name: String?,
    val active: Boolean?,
    val status: UserStatus?,
    val statusConnection: UserStatus?,
    val utcOffset: Float?,
    val emails: List<Email>?,
    val roles: List<String>?
) : BaseUser