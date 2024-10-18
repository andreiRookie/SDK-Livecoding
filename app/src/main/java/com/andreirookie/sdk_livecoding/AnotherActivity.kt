package com.andreirookie.sdk_livecoding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AnotherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.another_activity_layout)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, FragmentWithNumber.create(PRIME_FRAG))
            .addToBackStack(FragmentWithNumber.FRAGMENT_TAG)
            .commit()
    }

    companion object {
        const val PRIME_FRAG = 1
    }
}