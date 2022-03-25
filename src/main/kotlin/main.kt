data class Post(
    var id: Int,
    val ownerId: Int,
    var groupId: Int,
    val date: String,
    var text: String
)

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

fun main() {
    // сначала add
    // специльно присвоил идиотские id пусть метод сам разберется, чтобы было подряд типа
    val first = Post(
        id = 1,
        ownerId = 2,
        groupId = 11,
        date = "02 of November",
        text = "курс по Kotlin читается отвратительно"
    )
    val second = Post(
        id = 3,
        ownerId = 3,
        groupId = 18,
        date = "03 of November",
        text = "действительно отвратительно"
    )
    val some = Post(
        id = 8,
        ownerId = 5,
        groupId = 21,
        date = "08 of May",
        text = "просто ужасно"
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
        date = "18 of May",
        text = "да, я тоже ничего не понимаю"
    )
    var answer = update(newPost)
    println(answer)
    println("_______________________")
    printArr(list)
}






