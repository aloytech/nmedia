package ru.netology.nmedia

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.PostCardBinding

class PostViewHolder(
    private val binding: PostCardBinding,
    private val onInteractionListener: OnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            authorTextView.text = post.author
            publishedTextView.text = post.published
            postText.text = post.content
            avatarView.setImageResource(R.drawable.ic_launcher_foreground)
            countLikesView.text = post.likesToString()
            countRepostView.text = post.repostsToString()
            watchesView.text = post.watchesToString()
            if (post.likedByMe) {
                likeButton.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                likeButton.setImageResource(R.drawable.ic_outline_favorite_border_24)
            }
            likeButton.setOnClickListener {
                onInteractionListener.onLikeListener(post.id)
            }
            repostButton.setOnClickListener {
                onInteractionListener.onRepostListener(post.id)
            }
            menuButton.setOnClickListener{
                PopupMenu(it.context,it).apply {
                    inflate(R.menu.popup_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.removeItem -> {
                                onInteractionListener.onRemoveListener(post.id)
                                true
                            }
                            R.id.editItem -> {
                                onInteractionListener.onEditItem(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }
}