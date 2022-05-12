package com.developer.android.rawg.main.ui

import android.os.Bundle
import com.developer.android.rawg.R
import com.developer.android.rawg.common.mvp.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val allGamesFragment = AllGamesFragment()
        changeFragment(allGamesFragment, R.id.fragment_container)
    }
}