package com.lixicode.toolbarheightdemo

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val KEY_FIX_PAGE = "key_fixed"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fixDemoIfNeed()

        setSupportActionBar(toolbar)

    }

    private fun fixDemoIfNeed() {
        intent?.takeIf {
            it.getBooleanExtra(KEY_FIX_PAGE, false)
        }?.run {
            val field = Toolbar::class.java.getDeclaredField("mButtonGravity")
            field.isAccessible = true
            field.set(toolbar, Gravity.CENTER_VERTICAL)

            fixed_button.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        menu.findItem(R.id.action_settings).run {
            actionView?.let {
                it as? Button
            }?.also {
                it.text = title
            }?.setOnClickListener {
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun fixDemo(view: View) {


        startActivity(Intent(this, MainActivity::class.java).also {
            it.putExtra(KEY_FIX_PAGE, true)
        })
    }
}
