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
            date = "18 of May",
            text = "да, я тоже ничего не понимаю"
        )
        //act
        var res = update(newPost)
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
            date = "18 of May",
            text = "да, я тоже ничего не понимаю"
        )
        //act
        var res = update(newPost)
        println(list.size)
        //assert
        assertEquals(false, res)
    }
}