package ru.netology.nmedia

interface OnInteractionListener {
    fun onLikeListener (id:Int)
    fun onRepostListener (id:Int)
    fun onRemoveListener (id:Int)
}