package chat.rocket.core.internal.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class MessagePayload(val message: MessageInternal) {
    @JsonSerializable
    data class MessageInternal(
            @Json(name = "_id") val id: String,
            @Json(name = "rid") val roomId: String,
            @Json(name = "msg") val message: String?
            )

    companion object {
        fun create(id: String, roomId: String, message: String) = MessagePayload(MessageInternal(id, roomId, message))
    }
}