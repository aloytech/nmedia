package ru.netology.nmedia

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.NewPostActivityBinding

class NewPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = NewPostActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newContent.requestFocus()
        intent?.let {
            val oldContent = it.getStringExtra("content")
            if (!oldContent.isNullOrBlank()) {
                binding.newContent.setText(oldContent)
            }
        }
        binding.saveButton.setOnClickListener {
            if (binding.newContent.text.isNullOrBlank()) {
                setResult(Activity.RESULT_CANCELED, intent)
            } else {
                val content = binding.newContent.text.toString()
                intent.putExtra(Intent.EXTRA_TEXT, content)
                setResult(Activity.RESULT_OK, intent)
            }
            finish()
        }
        binding.declineButton.setOnClickListener {
            binding.newContent.text.clear()
        }
    }
}