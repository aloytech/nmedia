package ru.netology.nmedia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
private val empty = Post(
    id = 0,
    author = "",
    content = "",
    published = "",
    likeCount = 0,
    repostCount = 0,
    watchesCount = 0,
    likedByMe = false
)
class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun likeDislike(id: Int) = repository.likeDislike(id)
    fun repost(id: Int) = repository.repost(id)
    fun removeById(id: Int) = repository.removeById(id)
    fun save(){
        edited.value?.let{
            repository.save(it)
        }
        edited.value = empty
    }
    fun changeContent (content: String){
        edited.value?.let{
            val text = content.trim()
            if (it.content == text){
                return
            }
            edited.value = it.copy(content = text)
        }
    }
}