package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.PostCardBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(viewModel::likeDislike, viewModel::repost, viewModel::removeById)

        binding.postRecycler.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        binding.saveButton.setOnClickListener {
            with(binding.newContent) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(this@MainActivity, R.string.empty_content, Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text.toString())
                viewModel.save()
                setText("")
                clearFocus()
                AndroidUtils.hideKeboard(this)
            }
        }
        binding.newContent.addTextChangedListener {
            binding.groupButton.visibility = View.VISIBLE
        }
        binding.declineButton.setOnClickListener {
            with(binding.newContent){
                setText("")
                clearFocus()
                AndroidUtils.hideKeboard(this)
                binding.groupButton.visibility = View.INVISIBLE
            }
        }
    }
}
