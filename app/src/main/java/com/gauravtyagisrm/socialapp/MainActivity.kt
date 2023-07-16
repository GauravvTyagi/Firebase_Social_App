package com.gauravtyagisrm.socialapp

import android.app.DownloadManager.Query
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.gauravtyagisrm.socialapp.daos.PostDao
import com.gauravtyagisrm.socialapp.databinding.ActivityMainBinding
import com.gauravtyagisrm.socialapp.models.Post

class MainActivity : AppCompatActivity(), IPostAdapter {
    public lateinit var postDao: PostDao
    private lateinit var adapter: PostAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postDao=PostDao()
        binding.fab.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            startActivity(intent)
        }
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        val postCollectios = postDao.postCollections
        val query = postCollectios.orderBy(
            "createdAt",
            com.google.firebase.firestore.Query.Direction.DESCENDING
        )
        val recyclerViewOptions =
            FirestoreRecyclerOptions.Builder<Post>().setQuery(query, Post::class.java).build()
        adapter = PostAdapter(recyclerViewOptions,this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onLikeClicked(postId: String) {
        postDao.updateLikes((postId))
    }
}

