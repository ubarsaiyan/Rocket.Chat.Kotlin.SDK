package chat.rocket.core.rxjava

import chat.rocket.common.model.User
import chat.rocket.core.RocketChatClient
import chat.rocket.core.internal.rest.me
import io.reactivex.Single
import kotlinx.coroutines.experimental.rx2.rxSingle

fun RocketChatClient.me(): Single<User> =
    rxSingle {
        me()
    }