package com.ark.schoternews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ark.schoternews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var navHostFragment : NavHostFragment
    private lateinit var navController : NavController
    private lateinit var listenerNavController :  NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initialize()
        listener()
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listenerNavController)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listenerNavController)
    }


    private fun initialize(){
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        listenerNavController = NavController.OnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment){
                binding.tvFragmentTitle.text = resources.getString(R.string.home_fragment)
                binding.ivBackButton.visibility = View.GONE
                binding.ivProfile.visibility = View.VISIBLE
            }

            if (destination.id == R.id.profileFragment){
                binding.tvFragmentTitle.text = resources.getString(R.string.profile_fragment)
                binding.ivBackButton.visibility = View.VISIBLE
                binding.ivProfile.visibility = View.GONE
            }

            if (destination.id == R.id.detailNewsFragment){
                binding.tvFragmentTitle.text = resources.getString(R.string.detail_fragment)
                binding.ivBackButton.visibility = View.VISIBLE
                binding.ivProfile.visibility = View.GONE
            }
        }

    }

    private fun listener(){
        binding.ivProfile.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_profileFragment)
        }

        binding.ivBackButton.setOnClickListener {
            navController.popBackStack()
        }
    }

}