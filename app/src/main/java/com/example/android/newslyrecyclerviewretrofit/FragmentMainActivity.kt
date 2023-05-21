package com.example.android.newslyrecyclerviewretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.android.newslyrecyclerviewretrofit.databinding.ActivityFragmentMainBinding

class FragmentMainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFragmentMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_fragment_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.idFragmentContainer) as NavHostFragment
//        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
           /* when (destination.id) {
                R.id.loginFragment -> {
                    //hide bottom nav
                    binding.bottomNavigationView.visibility = View.GONE
                    LoadFrag()
                }
                R.id.splashFragment -> {
                    //hide bottom nav
                    binding.bottomNavigationView.visibility = View.GONE
                    LoadFrag()
                }
                else -> {
                    //show bottom nav
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    LoadFrag()
                }
            }*/
        loadFrag()


        }

    private fun loadFrag() {
        val fragmentContainer=findViewById<View>(R.id.idFragmentContainer)
        binding.apply {
            bottomNavBar.setupWithNavController(fragmentContainer.findNavController())
        }
    }
}
