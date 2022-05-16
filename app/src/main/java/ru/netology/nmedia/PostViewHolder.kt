package ru.netology.nmedia

import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.PostCardBinding

class PostViewHolder(
    private val binding: PostCardBinding,
    private val onLikeListener: OnLikeListener
): RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post){
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
            likeButton.setOnClickListener{
                onLikeListener(post)
            }
        }
    }
}