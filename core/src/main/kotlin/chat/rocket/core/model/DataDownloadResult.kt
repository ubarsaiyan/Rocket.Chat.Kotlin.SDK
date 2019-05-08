package chat.rocket.core.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class DataDownloadResult(
    val requested: Boolean,
    val exportOperation: ExportOperation
)