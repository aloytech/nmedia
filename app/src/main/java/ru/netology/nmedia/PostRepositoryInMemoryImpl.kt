package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то нетология начиналасьс интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. http://netolo.gy/fyb",
        published = "27 апреля в 16:36",
        likeCount = 20632,
        repostCount = 1250,
        watchesCount = 3256000,
        likedByMe = false
    )
    private val data = MutableLiveData(post)
    override fun get(): LiveData<Post> = data

    override fun likeDislike(): Boolean {
        post = post.copy(likedByMe = !post.likedByMe)
        if (post.likedByMe) {
            post = post.copy(likeCount = post.likeCount + 1)
        } else {
            post = post.copy(likeCount = post.likeCount - 1)
        }
        data.value = post
        return post.likedByMe
    }

    override fun repost() {
        post = post.copy(repostCount = post.repostCount + 1)
        data.value = post
    }

    override fun likesToString(): String {
        return shortCountOut(post.likeCount)
    }

    override fun repostsToString(): String {
        return shortCountOut(post.repostCount)
    }

    override fun watchesToString(): String {
        return shortCountOut(post.watchesCount)
    }

    private fun shortCountOut(count: Int): String {
        return when (count) {
            in 0..999 -> {
                count.toString()
            }
            in 1000..9999 -> {
                val s = (count / 1000).toString()
                val h = (count % 1000 / 100).toString()
                "$s,$h" + "K"
            }
            in 10000..999999 -> {
                val s = (count / 1000).toString()
                "$s" + "K"
            }
            else -> {
                val m = (count / 1000000).toString()
                val s = (count % 1000000 / 100000)
                "$m,$s" + "M"
            }
        }
    }
}