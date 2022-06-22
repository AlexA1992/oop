import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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
    var attachment: Attachment
)

//класс искючения
class PostNotFoundException(massage: String) : Exception() {
    override val message: String?
        get() = "No such post (("
}

//класс коммент
class Comment(
    val commentId: Int,
    val postId: Int,
    val authorId: Int,
    var date: String,
    var text: String
) {
    fun addComment(list: MutableList<Post>, comment: Comment, commentList: MutableList<Comment>) {
        for (thispost in list) {
            if (comment.postId == thispost.id) {
                commentList.add(comment)
                println("Добавлено")
                break
            } else {
                throw PostNotFoundException("0")
            }
        }
    }
}

// это коллекция постов
object WallService {
    internal var list: MutableList<Post> = emptyArray<Post>().toMutableList()

    fun add(post: Post): MutableList<Post> {
        var newPost: Post = post
        if (!list.isEmpty()) {
            //post.id = list.last().id + 1
            newPost = post.copy(id = list.last().id + 1)
        } else {
            newPost.id = 1
        }
        list.add(newPost)
        return list
    }

    fun update(post: Post): Boolean {
        list.forEach() {
            if (it.id == post.id) {
                it.text = post.text
                return true
            }
        }
        return false
    }
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
    var first = Post(
        id = 1,
        ownerId = 2,
        text = "курс по Kotlin читается отвратительно",
        attachment = videoAttach1
    )
    var second = Post(
        id = 3,
        ownerId = 3,
        text = "действительно отвратительно",
        attachment = audioAttach1
    )
    var some = Post(
        id = 8,
        ownerId = 5,
        text = "просто ужасно",
        attachment = photoAttach1
    )
    WallService.add(first)
    WallService.add(second)
    WallService.add(some)
    printArr(WallService.list)

    // теперь update
    // создаем Post c id = 2 (такой id есть в системе), его и будем изменять
    // даем ему все другие данные, но поменяются только нужные
    // поэтому для id=2  ответом будет true, а для id=5  ответом будет false
    var newPost = Post(
        id = 2,
        ownerId = 6,
        text = "да, я тоже ничего не понимаю",
        attachment = docAttach1
    )
    val answer = WallService.update(newPost)
    println(answer)
    println("_______________________")
    printArr(WallService.list)

    //это массив для Attachment
    var theArray: Array<Attachment> = arrayOf()
    val toPrint = object {
        fun addToPrint(
            attachment1: Attachment,
            attachment2: Attachment,
            attachment3: Attachment,
            attachment4: Attachment,
            attachment5: Attachment,
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

    //печать комментов
    fun printComments(commentList: MutableList<Comment>) {
        for (comment in commentList) {
            println(comment.text)
        }
    }

    //создаем список комментов
    val commentsList = mutableListOf<Comment>()
    //создаем объекты Comment
    val comment1 = Comment(1, 1, 1, "12.01.2022", "классный пост")
    val comment2 = Comment(2, 20, 1, "13.01.2022", "ужасный пост")
    //пытаемся добавить оба. Добавится только 1
    try {
        comment1.addComment(WallService.list, comment1, commentsList)
        comment2.addComment(WallService.list, comment2, commentsList)
    } catch (e: PostNotFoundException) {
        println(e.message)
    }
    printComments(commentsList)
}








