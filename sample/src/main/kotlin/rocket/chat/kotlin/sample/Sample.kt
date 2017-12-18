package rocket.chat.kotlin.sample

import chat.rocket.common.model.BaseRoom
import chat.rocket.common.model.Token
import chat.rocket.common.util.PlatformLogger
import chat.rocket.core.RocketChatClient
import chat.rocket.core.TokenRepository
import chat.rocket.core.internal.rest.getRoomFavoriteMessages
import chat.rocket.core.internal.rest.login
import chat.rocket.core.internal.rest.sendMessage
import chat.rocket.core.model.Myself
import chat.rocket.core.rxjava.me
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.UUID

fun main(args: Array<String>) {
    val logger = object : PlatformLogger {
        override fun debug(s: String) {
            println(s)
        }

        override fun info(s: String) {
            println(s)
        }

        override fun warn(s: String) {
            println(s)
        }
    }

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()

    val client = RocketChatClient.create {
        httpClient = okHttpClient
        restUrl = HttpUrl.parse("http://localhost:3000/")!!
        websocketUrl = "ws://localhost:3000/websocket"
        tokenRepository = SimpleTokenRepository()
        platformLogger = logger
    }

    // using coroutines
    val job = launch(CommonPool) {
        val token = client.login("testuser", "testpass")
        logger.debug("Login: ${token.userId} - ${token.authToken}")

        val message = client.sendMessage(id = UUID.randomUUID().toString(),
                roomId = "GENERAL",
                message = "Sending message from SDK to #general and @here with url https://github.com/RocketChat/Rocket.Chat.Kotlin.SDK/")

        logger.debug("Sent: $message")

        /*pinMessage(client)

        getMeInfoByRx(client)

        val rooms = client.chatRooms()
        logger.debug("ChatRooms: $rooms")*/
    }

    // simple old callbacks
    /*client.serverInfo(object : Callback<ServerInfo> {
        override fun onSuccess(data: ServerInfo) {
            logger.debug("Server: $data")
        }

        override fun onError(error: RocketChatException) {
            error.printStackTrace()
        }
    })*/

    runBlocking {
        job.join()
    }
}

fun getMeInfoByRx(client: RocketChatClient) {
    // using RxJava2
    val myself: Single<Myself> = client.me()
    myself
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.newThread())
            .subscribe { self ->
                println("Self: $self")
            }
}

suspend fun pinMessage(client: RocketChatClient) {
        val result = client.getRoomFavoriteMessages("GENERAL", BaseRoom.RoomType.PUBLIC, 0)
        println("favoriteMessages: $result")
}

class SimpleTokenRepository : TokenRepository {
    private var savedToken: Token? = null
    override fun save(token: Token) {
        savedToken = token
    }

    override fun get(): Token? {
        return savedToken
    }
}