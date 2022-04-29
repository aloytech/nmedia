package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то нетология начиналасьс интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. http://netolo.gy/fyb",
            published = "27 апреля в 16:36",
            likeCount = 999999,
            repostCount = 1099,
            watchesCount = 33256000,
            likedByMe = false
        )
        initViews(post, binding)
        initButtons(post, binding)
    }

    private fun initButtons(post: Post, binding: ActivityMainBinding) {
        with(binding) {
            likeButton.setOnClickListener {
                if (post.likeDislike()) {
                    likeButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                } else {
                    likeButton.setImageResource(R.drawable.ic_outline_favorite_border_24)
                }
                countLikesView.text = post.likesToString()
            }
            repostButton.setOnClickListener {
                post.repostCount++
                countRepostView.text = post.repostsToString()
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
                likeButton.setImageResource(R.drawable.ic_outline_favorite_border_24)
            }

        }
    }
}