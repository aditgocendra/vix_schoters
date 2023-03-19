package com.ark.schoternews.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.ark.schoternews.R
import com.ark.schoternews.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.ivBackButton.setOnClickListener {
            navController.popBackStack()
        }

        // Inflate the layout for this fragment
        return binding.root
    }


}