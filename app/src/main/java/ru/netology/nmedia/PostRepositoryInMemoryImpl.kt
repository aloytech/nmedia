package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то нетология начиналасьс интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. http://netolo.gy/fyb",
        published = "27 апреля в 16:36",
        likeCount = 999,
        repostCount = 9999,
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


}