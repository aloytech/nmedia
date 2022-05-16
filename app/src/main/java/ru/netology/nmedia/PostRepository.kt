package ru.netology.nmedia

import androidx.lifecycle.LiveData

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeDislike(id: Int): Boolean
    fun repost(id: Int)
}