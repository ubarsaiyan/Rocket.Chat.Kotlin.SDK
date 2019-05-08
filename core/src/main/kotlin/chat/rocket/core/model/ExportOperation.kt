package chat.rocket.core.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ExportOperation(
    val userId : String,
    val roomList: Any?,
    val status: String,
    val exportPath: String?,
    val assetsPath: String?,
    val fileList: Any?,
    val generatedFile: String?,
    val fullExport: Boolean
)
