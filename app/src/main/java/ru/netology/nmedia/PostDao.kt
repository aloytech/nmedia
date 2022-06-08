package ru.netology.nmedia

interface PostDao {
    fun getAll(): List<Post>
    fun likeDislike(id: Int)
    fun repost(id: Int)
    fun removeById(id: Int)
    fun save(post: Post): Post
    fun showPost(id: Int): Post
}