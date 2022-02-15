package com.codingtok.hmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.codingtok.hmovies.databinding.ActivityMainBinding
import com.codingtok.hmovies.ui.viewmodel.MoviesViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        viewModel.page.observe(this) { totalResults ->
            totalResults?.let {
                Toast.makeText(applicationContext, "Total result: $it", Toast.LENGTH_SHORT).show()
            }

        }
    }
}