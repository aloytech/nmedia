package ru.netology.nmedia

import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun likeDislike() = repository.likeDislike()
    fun repost() = repository.repost()
}