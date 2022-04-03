package com.shoppi.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_main)
        //Theme의 color를 참조를 초기화
        bottomNavigationView.itemIconTintList = null

        //safe call operator (?) 를 사용 -> find, get으로 시작하는 method는 null을 반환할 수 있으므로
        val navController = supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()
        navController?.let {
            //bottomNavigationView와 fragment container view의 navhostfragment를 연결
            //destination의 이동을 관리함
            //bottom navigation을 터치했을 때 화면 이동이 이뤄질 수 있음
            bottomNavigationView.setupWithNavController(it)
        }
    }
}