package ru.netology.nmedia

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: PostViewModel by viewModels()
    private val editPostLauncher = registerForActivityResult(NewPostResultContract()) { result ->
        result ?: return@registerForActivityResult
        viewModel.changeContent(result)
        viewModel.save()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostAdapter(object : OnInteractionListener {
            override fun onLikeListener(id: Int) {
                viewModel.likeDislike(id)
            }

            override fun onRepostListener(id: Int) {
                viewModel.repost(id)
            }

            override fun onRemoveListener(id: Int) {
                viewModel.removeById(id)
            }

            override fun onEditItem(post: Post) {
                viewModel.edit(post)
                editPostLauncher.launch(post.content)
            }

            override fun launchVideoLink(link: String) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(intent)
            }

        })

        binding.postRecycler.adapter = adapter
        binding.newPost.setOnClickListener {

            editPostLauncher.launch("")
        }
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }
}
