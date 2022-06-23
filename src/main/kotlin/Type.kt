import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

enum class TYPE {
    VIDEO, AUDIO, PHOTO, GIF, DOC
}

data class Post(
    var id: Int,
    var ownerId: Int,
    val fromId: Int = 1,
    val createdBy: Int = 1,
    val groupId: Int = 5,
    var date: String = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")),
    var text: String?,
    val replyOwnerId: Int? = 1,
    val replyPostId: Int? = 1,
    val friendsOnly: Boolean = true,
    val singerId: Int = 1,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = true,
    val isPinned: Boolean = true,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val postponedId: Int? = 2,
    var attachment: Attachment,
) {
    override fun toString(): String {
        return "Post(id=$id, ownerId=$ownerId, fromId=$fromId, createdBy=$createdBy, groupId=$groupId, date='$date', text=$text, replyOwnerId=$replyOwnerId, replyPostId=$replyPostId, friendsOnly=$friendsOnly, singerId=$singerId, canPin=$canPin, canDelete=$canDelete, canEdit=$canEdit, isPinned=$isPinned, markedAsAds=$markedAsAds, isFavorite=$isFavorite, postponedId=$postponedId, attachment=${attachment.thisPostList})"
    }
}

object allAttaches {
    internal var attachList: MutableList<AttachType> = emptyArray<AttachType>().toMutableList()
}

//делаем attachment
data class Attachment(
    val videoCount: Int = 0,
    val audioCount: Int = 0,
    val photoCount: Int = 0,
    val gifCount: Int = 0,
    val docCount: Int = 0,
) {
    companion object val thisPostList: MutableList<AttachType> = mutableListOf<AttachType>()

    init {
        //видео
        if (videoCount > 0) {
            println("There are some Videos")
            val type = TYPE.VIDEO
            for (i in 1..videoCount) {
                val theAttach = VideoAttach(type)
                thisPostList.add(theAttach)
                allAttaches.attachList.add(theAttach)
            }
        }
        //аудио
        if (audioCount > 0) {
            println("There are some Audios")
            val type = TYPE.AUDIO
            for (i in 1..audioCount) {
                val theAttach = AudioAttach(type)
                thisPostList.add(theAttach)
                allAttaches.attachList.add(theAttach)
            }
        }
        //фото
        if (photoCount > 0) {
            println("There are some Photos")
            val type = TYPE.PHOTO
            for (i in 1..photoCount) {
                val theAttach = PhotoAttach(type)
                thisPostList.add(theAttach)
                allAttaches.attachList.add(theAttach)
            }
        }
        //гиф
        if (gifCount > 0) {
            println("There are some Gifs")
            val type = TYPE.GIF
            for (i in 1..gifCount) {
                val theAttach = GifAttach(type)
                thisPostList.add(theAttach)
                allAttaches.attachList.add(theAttach)
            }
        }
        //доки
        if (docCount > 0) {
            println("There are some Docs")
            val type = TYPE.DOC
            for (i in 1..docCount) {
                val theAttach = DocAttach(type)
                thisPostList.add(theAttach)
                allAttaches.attachList.add(theAttach)
            }
        }
    }
}

class theObject(
    val type: String,
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
)

abstract class AttachType(_type: TYPE) {
    open val type = _type.toString()
    open val info by lazy {theObject(
        type,
        (1..100).random(),
        (1..100).random(),
        (1..100).random(),
        (1..100).random(),
    )}

    override fun toString(): String {
        return "AttachType(type='$type', info='type - ${info.type}, id - ${info.id}, albumId - ${info.albumId}, ownerId - ${info.ownerId}, userId - ${info.userId}')"
    }
}

class VideoAttach(_type: TYPE) : AttachType(_type) {
    override val type = super.type
    override val info = super.info
//    init {
//        println(this.id)
//        println(this.albumId)
//        println(this.ownerId)
//    }
}

class AudioAttach(_type: TYPE) : AttachType(_type) {
    override val type = super.type
    override val info = super.info
//    init {
//        println(this.id)
//        println(this.albumId)
//        println(this.ownerId)
//    }
}

class PhotoAttach(_type: TYPE) : AttachType(_type) {
    override val type = super.type
    override val info = super.info
//    init {
//        println(this.id)
//        println(this.albumId)
//        println(this.ownerId)
//    }
}

class GifAttach(_type: TYPE) : AttachType(_type) {
    override val type = super.type
    override val info = super.info
//    init {
//        println(this.id)
//        println(this.albumId)
//        println(this.ownerId)
//    }
}

class DocAttach(_type: TYPE) : AttachType(_type) {
    override val type = super.type
    override val info = super.info
//    init {
//        println(this.id)
//        println(this.albumId)
//        println(this.ownerId)
//    }
}
