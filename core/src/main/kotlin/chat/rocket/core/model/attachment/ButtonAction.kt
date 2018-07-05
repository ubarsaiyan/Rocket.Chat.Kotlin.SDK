package chat.rocket.core.model.attachment

import com.squareup.moshi.Json

data class ButtonAction(
        override val type: String?,
        val text: String?,
        override val url: String,
        @Json(name = "url_view_type") val urlViewType: String?
) : Action {
}