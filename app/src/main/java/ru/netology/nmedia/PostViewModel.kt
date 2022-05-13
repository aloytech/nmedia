package ru.netology.nmedia

import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun likeDislike() = repository.likeDislike()
    fun repost() = repository.repost()
    fun likesToString() = repository.likesToString()
    fun watchesToString() = repository.watchesToString()
    fun repostsToString() = repository.repostsToString()
}