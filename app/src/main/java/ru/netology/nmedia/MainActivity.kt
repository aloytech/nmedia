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
        viewModel.data.observe(this) {
            initViews(viewModel, binding)
            initButtons(viewModel, binding)
        }


    }

    private fun initButtons(viewModel: PostViewModel, binding: ActivityMainBinding) {
        with(binding) {
            likeButton.setOnClickListener {
                if (viewModel.likeDislike()) {
                    likeButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    likeButton.setImageResource(R.drawable.ic_outline_favorite_border_24)
                }
                countLikesView.text = viewModel.likesToString()
            }
            repostButton.setOnClickListener {
                viewModel.repost()
                countRepostView.text = viewModel.repostsToString()
            }
        }
    }

    private fun initViews(viewModel: PostViewModel, binding: ActivityMainBinding) {
        with(binding) {
            authorTextView.text = viewModel.data.value?.author
            publishedTextView.text = viewModel.data.value?.published
            postText.text = viewModel.data.value?.content
            avatarView.setImageResource(R.drawable.ic_launcher_foreground)
            countLikesView.text = viewModel.likesToString()
            countRepostView.text = viewModel.repostsToString()
            watchesView.text = viewModel.watchesToString()
            if (viewModel.data.value?.likedByMe == true) {
                likeButton.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                likeButton.setImageResource(R.drawable.ic_outline_favorite_border_24)
            }

        }
    }
}