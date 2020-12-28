package ru.andvl.avitotask

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val f = supportFragmentManager.findFragmentById(R.id.main_frame)
		if (f != null) {
			return
		}

		supportFragmentManager.beginTransaction()
			.add(R.id.main_frame, NumbersFragment.newInstance()).commit()
	}

	companion object {
		private const val P_COLM = 2
		private const val L_COLM = 4
	}
}