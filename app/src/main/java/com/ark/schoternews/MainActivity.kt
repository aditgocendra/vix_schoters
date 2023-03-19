package com.ark.schoternews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ark.schoternews.core.utility.RetrofitInstance
import com.ark.schoternews.data.datasource.local.AppDatabase
import com.ark.schoternews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val newsRemote by lazy { RetrofitInstance.api }
    val dbLocal by lazy { AppDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}