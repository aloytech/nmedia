package ru.netology.nmedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class PostRepositorySQLiteImpl(private val postDao: PostDao) : PostRepository {
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)

    init {
        posts = postDao.getAll()
        data.value = posts
    }

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeDislike(id: Int) {
        postDao.likeDislike(id)
        posts = posts.map {
            if (it.id != id) it else it.copy(
                likedByMe = !it.likedByMe,
                likeCount = if (it.likedByMe) it.likeCount - 1 else it.likeCount + 1
            )
        }
        data.value = posts

    }

    override fun repost(id: Int) {
        postDao.repost(id)
        posts = posts.map {
            if (it.id != id) it else it.copy(repostCount = it.repostCount + 1)
        }
        data.value = posts
    }

    override fun removeById(id: Int) {
        postDao.removeById(id)
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        val id = post.id
        val savedPost = postDao.save(post)
        posts = if (id == 0) {
            listOf(savedPost) + posts
        } else {
            posts.map {
                if (it.id != id) it else savedPost
            }
        }
        data.value = posts
    }

    override fun showPost(id: Int): Post {
        return postDao.showPost(id)
    }
}