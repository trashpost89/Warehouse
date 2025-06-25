package com.example.warehouse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.warehouse.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.example.warehouse.R

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Убедитесь, что в вашем layout есть toolbar с этим ID
        setSupportActionBar(binding.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        // Убедитесь, что у вас есть фрагмент-контейнер с этим ID в activity_main.xml
        val navController = findNavController(R.id.nav_host_fragment)

        // Убедитесь, что эти ID существуют в вашем файле навигации (nav_graph.xml)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.InventoryFragment,
                R.id.orderFragment,
                R.id.receiptFragment
            ),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}