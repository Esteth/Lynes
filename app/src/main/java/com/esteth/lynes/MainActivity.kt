package com.esteth.lynes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    NavigationUI.setupActionBarWithNavController(
        this,
        findNavController(R.id.nav_host_fragment))
  }

  override fun onSupportNavigateUp(): Boolean =
      findNavController(R.id.nav_host_fragment).navigateUp()
}
