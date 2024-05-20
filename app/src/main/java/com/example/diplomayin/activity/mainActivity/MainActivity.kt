package com.example.diplomayin.activity.mainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.data.util.Constants.Companion.NAV_DRAWER_CATEGORY_KEY
import com.example.diplomayin.R
import com.example.diplomayin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.apply {
            bottomNavigationView.setupWithNavController(navController)
            toggle = ActionBarDrawerToggle(
                this@MainActivity,
                root,
                R.string.nav_open,
                R.string.nav_close
            )

            root.addDrawerListener(toggle)
            toggle.syncState()

            setSupportActionBar(toolbar)
            supportActionBar?.title = ""
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                val bundle = Bundle()

                bundle.putString(
                    NAV_DRAWER_CATEGORY_KEY,
                    it.title.toString()
                )

                navController.navigate(
                    R.id.newsFragment, bundle
                )

                binding.root.closeDrawer(GravityCompat.START)
                true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)

    }
}