package com.example.kreaz

import android.os.Bundle
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import androidx.navigation.NavController
import androidx.navigation.findNavController

import androidx.navigation.ui.setupActionBarWithNavController
/*
import com.example.kreaz.ui.main.SectionsPagerAdapter
*/
import com.example.kreaz.databinding.ActivityMainBinding
import androidx.navigation.ui.NavigationUI

import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigation

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.categoriesFragment, R.id.offersFragment, R.id.branchesFragment2
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)




        visibilityNavElements(navController)


    }


    private fun visibilityNavElements(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.cartFragment -> binding.bottomNavigation.visibility = View.GONE
                else -> binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
    }

}