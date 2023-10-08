package com.example.traveler.uis.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.traveler.R
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activate_account)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val nav_view = findViewById<NavigationView>(R.id.nav_view)
        setSupportActionBar(toolbar)

        val navigationController = Navigation.findNavController(this , R.id.fragment)
        NavigationUI.setupWithNavController(nav_view , navigationController)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        NavigationUI.setupActionBarWithNavController(this, navigationController,drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this , R.id.fragment),
            findViewById<DrawerLayout>(R.id.drawer_layout)
        )
    }
}