package com.gauravtyagisrm.socialapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gauravtyagisrm.socialapp.daos.PostDao
import com.gauravtyagisrm.socialapp.databinding.ActivityCreatePostBinding
import com.gauravtyagisrm.socialapp.databinding.ActivityMainBinding

class CreatePostActivity : AppCompatActivity() {
    private var postDao= PostDao()
    private lateinit var binding: ActivityCreatePostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postDao= PostDao()
        binding.postButton.setOnClickListener{
            val input = binding.postInput.text.toString().trim()
            if (input.isNotEmpty()){
         postDao.addPost(input)
                finish()

            }

        }
        val intent= Intent(this,MainActivity::class.java)
    }

    override fun finish() {
        super.finish()
    }
}