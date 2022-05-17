package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = listOf(
        Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то нетология начиналасьс интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. http://netolo.gy/fyb",
            published = "27 апреля в 16:36",
            likeCount = 999,
            repostCount = 9999,
            watchesCount = 3256000,
            likedByMe = false
        ),
        Post(
            id = 2,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то нетология начиналасьс интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. http://netolo.gy/fyb",
            published = "16 мая в 16:36",
            likeCount = 10000,
            repostCount = 999999,
            watchesCount = 22600,
            likedByMe = true
        ),
        Post(
            id = 3,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то нетология начиналасьс интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. http://netolo.gy/fyb",
            published = "16 мая в 20:36",
            likeCount = 23,
            repostCount = 51,
            watchesCount = 227600,
            likedByMe = false
        )
    )
    private val data = MutableLiveData(posts)
    override fun getAll(): LiveData<List<Post>> = data

    override fun likeDislike(id: Int) {

        posts = posts.map {
            if (it.id != id) {
                it
            } else {
                if (it.likedByMe) {
                    it.copy(likedByMe = !it.likedByMe, likeCount = it.likeCount - 1)
                } else {
                    it.copy(likedByMe = !it.likedByMe, likeCount = it.likeCount + 1)
                }
            }
        }
        data.value = posts
    }

    override fun repost(id: Int) {
        posts = posts.map {
            if (it.id != id) it else it.copy(repostCount = it.repostCount + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Int) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }


}