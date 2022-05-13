package ru.netology.nmedia

import androidx.lifecycle.LiveData

interface PostRepository {
    fun get(): LiveData<Post>
    fun likeDislike(): Boolean
    fun repost()
    fun likesToString(): String
    fun repostsToString(): String
    fun watchesToString(): String
}