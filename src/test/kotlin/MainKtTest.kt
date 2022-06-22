import org.junit.Assert.*
import org.junit.Test

class MainKtTest {

    @Test
    fun add() {
        //arrange
        val first = Post(
            id = 1,
            ownerId = 2,
            groupId = 11,
            date = 161234657,
            text = "курс по Kotlin читается отвратительно",
            attachment = VideoAttach(Type.VIDEO,2,3, 1,2),
        )
        val second = Post(
            id = 3,
            ownerId = 3,
            groupId = 18,
            date = 161234138,
            text = "действительно отвратительно",
            attachment = AudioAttach(Type.AUDIO,1,13, 2,1),
        )
        val some = Post(
            id = 8,
            ownerId = 5,
            groupId = 21,
            date = 161234254,
            text = "просто ужасно",
            attachment = GifAttach(Type.GIF,3,5, 2,3),
        )
        //act
        add(first)
        add(second)
        add(some)
        println(list.size)
        val res = list.last().id != 0
        //assert
        assertEquals(list.last().id != 0, res)
    }

    @Test
    fun update_true() {
        //arrange
        val newPost = Post(
            id = 2,
            ownerId = 6,
            groupId = 22,
            date = 161234122,
            text = "да, я тоже ничего не понимаю",
            attachment = PhotoAttach(Type.PHOTO,5,8, 4,4),
        )
        //act
        val res = update(newPost)
        println(list.size)
        //assert
        assertEquals(true, res)
    }

    @Test
    fun update_false() {
        //arrange
        val newPost = Post(
            id = 9,
            ownerId = 6,
            groupId = 22,
            date = 161234120,
            text = "да, я тоже ничего не понимаю",
            attachment = DocAttach(Type.DOC,5,7, 6,6),
        )
        //act
        val res = update(newPost)
        println(list.size)
        //assert
        assertEquals(false, res)
    }
}