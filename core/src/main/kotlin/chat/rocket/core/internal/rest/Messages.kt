package chat.rocket.core.internal.rest

import chat.rocket.common.model.BaseRoom
import chat.rocket.core.RocketChatClient
import chat.rocket.core.internal.RestResult
import chat.rocket.core.internal.model.MessagePayload
import chat.rocket.core.model.Attachment
import chat.rocket.core.model.Message
import chat.rocket.core.model.PagedResult
import com.squareup.moshi.Types
import okhttp3.FormBody
import okhttp3.MediaType
import okhttp3.RequestBody

suspend fun RocketChatClient.pinMessage(messageId: String): Message {
    val body = FormBody.Builder().add("messageId", messageId).build()

    val httpUrl = requestUrl(restUrl, "chat.pinMessage").build()

    val request = requestBuilder(httpUrl).post(body).build()

    val type = Types.newParameterizedType(RestResult::class.java,
            Message::class.java)

    return handleRestCall<RestResult<Message>>(request, type).result()
}

suspend fun RocketChatClient.getRoomFavoriteMessages(roomId: String,
                                                     roomType: BaseRoom.RoomType,
                                                     offset: Int): PagedResult<List<Message>> {
    val userId = tokenRepository.get()?.userId

    val httpUrl = requestUrl(restUrl, getRestApiMethodNameByRoomType(roomType, "messages"))
            .addQueryParameter("roomId", roomId)
            .addQueryParameter("offset", offset.toString())
            .addQueryParameter("query", "{\"starred._id\":{\"\$in\":[\"$userId\"]}}")
            .build()

    val request = requestBuilder(httpUrl).get().build()

    val type = Types.newParameterizedType(RestResult::class.java,
            Types.newParameterizedType(List::class.java, Message::class.java))
    val result = handleRestCall<RestResult<List<Message>>>(request, type)

    return PagedResult<List<Message>>(result.result(), result.total() ?: 0, result.offset() ?: 0)
}

suspend fun RocketChatClient.getRoomPinnedMessages(roomId: String,
                                                   roomType: BaseRoom.RoomType,
                                                   offset: Int? = 0): PagedResult<List<Message>> {
    val httpUrl = requestUrl(restUrl,
            getRestApiMethodNameByRoomType(roomType, "messages"))
            .addQueryParameter("roomId", roomId)
            .addQueryParameter("offset", offset.toString())
            .addQueryParameter("query", "{\"pinned\":true}")
            .build()

    val request = requestBuilder(httpUrl).get().build()

    val type = Types.newParameterizedType(RestResult::class.java,
            Types.newParameterizedType(List::class.java, Message::class.java))
    val result = handleRestCall<RestResult<List<Message>>>(request, type)

    return PagedResult<List<Message>>(result.result(), result.total() ?: 0, result.offset() ?: 0)
}

/**
 * Sends a new message
 *
 * @param id the new message ID
 * @param roomId the room where to send the message (works with all types)
 * @param message Optional text message to send
 * @return the new Message object
 */
suspend fun RocketChatClient.sendMessage(id: String,
                                         roomId: String,
                                         message: String): Message {
    val payload = MessagePayload.create(id, roomId, message)
    val adapter = moshi.adapter(MessagePayload::class.java)
    val payloadBody = adapter.toJson(payload)

    val contentType = MediaType.parse("application/json; charset=utf-8")
    val body = RequestBody.create(contentType, payloadBody)

    val url = requestUrl(restUrl, "chat.sendMessage").build()
    val request = requestBuilder(url).post(body).build()

    val type = Types.newParameterizedType(RestResult::class.java, Message::class.java)
    return handleRestCall<RestResult<Message>>(request, type).result()
}