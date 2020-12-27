package ru.andvl.avitotask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val f = supportFragmentManager.findFragmentById(R.id.main_frame)
		if (f != null) {
			return
		}
		supportFragmentManager.beginTransaction().add(R.id.main_frame, NumbersFragment.newInstance(2)).commit()
	}
}