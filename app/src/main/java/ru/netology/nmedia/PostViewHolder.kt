package ru.netology.nmedia

import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.view.View
import android.widget.PopupMenu
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.databinding.PostCardBinding

class PostViewHolder(
    private val binding: PostCardBinding,
    private val onInteractionListener: OnInteractionListener,
    private val registerForResult: ActivityResultLauncher<String?>
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            authorTextView.text = post.author
            publishedTextView.text = post.published
            postText.text = post.content
            avatarView.setImageResource(R.drawable.ic_launcher_foreground)
            likeButton.text = post.likesToString()
            repostButton.text = post.repostsToString()
            watchesIcon.text = post.watchesToString()
            likeButton.isChecked = post.likedByMe
            likeButton.setOnClickListener {
                onInteractionListener.onLikeListener(post.id)
            }
            repostButton.setOnClickListener {
                onInteractionListener.onRepostListener(post.id)
            }
            menuButton.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.popup_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.removeItem -> {
                                onInteractionListener.onRemoveListener(post.id)
                                true
                            }
                            R.id.editItem -> {
                                onInteractionListener.onEditItem(post)
                                registerForResult.launch(post.content)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
            if (post.video != null){
                videoLink.visibility = View.VISIBLE
                videoLink.setOnClickListener{
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(post.video))
                    it.context.startActivity(intent)
                }
            }
        }
    }
}