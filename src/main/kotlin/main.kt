data class Post(
    var id: Int,
    val ownerId: Int,
    val fromId: Int = 1,
    val createdBy: Int = 1,
    var groupId: Int,
    val date: Int,
    var text: String?,
    var replyOwnerId: Int? = 1,
    var replyPostId: Int? = 1,
    var friendsOnly: Boolean = true,
    var singerId: Int = 1,
    var canPin: Boolean = false,
    var canDelete: Boolean = false,
    var canEdit: Boolean = true,
    var isPinned: Boolean = true,
    var markedAsAds: Boolean = false,
    var isFavorite: Boolean = false,
    var postponedId: Int? = 2,
    var attachment: Attachment
)

// это коллекция постов
var posts = emptyArray<Post>()
val list: MutableList<Post> = posts.toMutableList()

fun add(post: Post): MutableList<Post> {
    if (!list.isEmpty()) {
        post.id = list.last().id + 1
        list.add(post)
    } else {
        post.id = 1
        list.add(post)
    }
    return list
}

fun update(post: Post): Boolean {
    for (postList in list) {
        if (postList.id == post.id) {
            postList.groupId = post.groupId
            postList.text = post.text
            return true
        }
    }
    return false
}

fun printArr(list: MutableList<Post>) {
    for (post in list) {
        println(post)
    }
}

//делаем attachment абстрактным
abstract class Attachment(type: Type, id: Int, albumId: Int, ownerId: Int, userId: Int) {
    abstract val type: Type
    abstract val id: Int
    abstract val albumId: Int
    abstract val ownerId: Int
    abstract val userId: Int
//    override fun toString(): String {
//        return super.toString()
//    }
}

// делаем дочерние классы
class VideoAttach(
    type: Type,
    override val id: Int,
    override val albumId: Int,
    override val ownerId: Int,
    override val userId: Int
) : Attachment(type, id, albumId, ownerId, userId) {
    override val type: Type = Type.VIDEO
}

class AudioAttach(
    type: Type,
    override val id: Int,
    override val albumId: Int,
    override val ownerId: Int,
    override val userId: Int
) : Attachment(type, id, albumId, ownerId, userId) {
    override val type: Type = Type.AUDIO
}

class PhotoAttach(
    type: Type,
    override val id: Int,
    override val albumId: Int,
    override val ownerId: Int,
    override val userId: Int
) : Attachment(type, id, albumId, ownerId, userId) {
    override val type: Type = Type.PHOTO
}

class GifAttach(
    type: Type,
    override val id: Int,
    override val albumId: Int,
    override val ownerId: Int,
    override val userId: Int
) : Attachment(type, id, albumId, ownerId, userId) {
    override val type: Type = Type.GIF
}

class DocAttach(
    type: Type,
    override val id: Int,
    override val albumId: Int,
    override val ownerId: Int,
    override val userId: Int
) : Attachment(type, id, albumId, ownerId, userId) {
    override val type: Type = Type.DOC
}

//создаем объекты для каждого наследника по одному
val videoAttach1: Attachment = VideoAttach(Type.VIDEO, 1, 1, 1, 1)
val audioAttach1: Attachment = AudioAttach(Type.AUDIO, 1, 1, 1, 1)
val photoAttach1: Attachment = PhotoAttach(Type.PHOTO, 1, 1, 1, 1)
val gifAttach1: Attachment = GifAttach(Type.GIF, 1, 1, 1, 1)
val docAttach1: Attachment = DocAttach(Type.DOC, 1, 1, 1, 1)

fun main() {
    // сначала add
    // специально присвоил идиотские id пусть метод сам разберется как выстроить их подряд
    val first = Post(
        id = 1,
        ownerId = 2,
        groupId = 11,
        date = 161234657,
        text = "курс по Kotlin читается отвратительно",
        attachment = videoAttach1
    )
    val second = Post(
        id = 3,
        ownerId = 3,
        groupId = 18,
        date = 161234138,
        text = "действительно отвратительно",
        attachment = audioAttach1
    )
    val some = Post(
        id = 8,
        ownerId = 5,
        groupId = 21,
        date = 161234254,
        text = "просто ужасно",
        attachment = photoAttach1
    )
    add(first)
    add(second)
    add(some)
    printArr(list)

    // теперь update
    // создаем Post c id = 2 (такой id есть в системе), его и будем изменять
    // даем ему все ругие данные, но поменяются только нужные
    // поэтому для id=2  ответом будет true, а для id=5  ответом будет false
    val newPost = Post(
        id = 2,
        ownerId = 6,
        groupId = 22,
        date = 161234122,
        text = "да, я тоже ничего не понимаю",
        attachment = docAttach1
    )
    var answer = update(newPost)
    println(answer)
    println("_______________________")
    printArr(list)

    //это массив для Attachment
    var theArray: Array<Attachment> = arrayOf()
    val toPrint = object {
        fun addToPrint(
            attachment1: Attachment,
            attachment2: Attachment,
            attachment3: Attachment,
            attachment4: Attachment,
            attachment5: Attachment
        ) {
            theArray = arrayOf(attachment1, attachment2, attachment3, attachment4, attachment5)
        }
    }
    toPrint.addToPrint(videoAttach1, audioAttach1, photoAttach1, gifAttach1, docAttach1)

    // функция для печати массива
    fun printArray(theArray: Array<Attachment>) {
        for (printed in theArray) {
            println("${printed.type} ${printed.id} ${printed.albumId} ${printed.ownerId} ${printed.userId}")
        }
    }
    printArray(theArray)
}




