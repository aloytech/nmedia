package ru.netology.nmedia

import org.junit.Assert.*
import org.junit.Test

class PostRepositoryInMemoryImplTest {


    @Test
    fun likesToStringAbove10K() {
        val expected = "20K"
        val repository: PostRepository = PostRepositoryInMemoryImpl()
        val actual = repository.likesToString()
        assertEquals(expected, actual)
    }

    @Test
    fun repostsToStringAbove1K() {
        val expected = "1,2K"
        val repository: PostRepository = PostRepositoryInMemoryImpl()
        val actual = repository.repostsToString()
        assertEquals(expected, actual)
    }

    @Test
    fun watchesToStringAbove1M() {
        val expected = "3,2M"
        val repository: PostRepository = PostRepositoryInMemoryImpl()
        val actual = repository.watchesToString()
        assertEquals(expected, actual)
    }
}