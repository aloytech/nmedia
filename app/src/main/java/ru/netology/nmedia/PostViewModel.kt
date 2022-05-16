package ru.netology.nmedia

import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeDislike(id: Int) = repository.likeDislike(id)
    fun repost(id: Int) = repository.repost(id)
}