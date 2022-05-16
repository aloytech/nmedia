package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()

        initButtons(viewModel, binding)

        viewModel.data.observe(this) { post ->
            initViews(post, binding)
        }
    }

    private fun initButtons(viewModel: PostViewModel, binding: ActivityMainBinding) {
        with(binding) {
            likeButton.setOnClickListener {
                viewModel.likeDislike()
            }
            repostButton.setOnClickListener {
                viewModel.repost()
            }
        }
    }

    private fun initViews(post: Post, binding: ActivityMainBinding) {
        with(binding) {
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

        }
    }
}