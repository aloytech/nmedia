package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostAdapter(object : OnInteractionListener{
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
            }

        })

        binding.postRecycler.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
        viewModel.edited.observe(this){
            if (it.id == 0){
                return@observe
            }
            with(binding.newContent){
                requestFocus()
                setText(it.content)
            }
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
                AndroidUtils.hideKeyboard(this)
                binding.groupButton.visibility = View.INVISIBLE
            }
        }
        binding.newContent.addTextChangedListener {
            binding.groupButton.visibility = View.VISIBLE
        }
        binding.declineButton.setOnClickListener {
            with(binding.newContent){
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                binding.groupButton.visibility = View.INVISIBLE
                viewModel.clearEdited()
            }
        }
    }
}
