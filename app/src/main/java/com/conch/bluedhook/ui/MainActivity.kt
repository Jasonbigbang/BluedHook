package com.conch.bluedhook.ui

import android.content.ComponentName
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.conch.bluedhook.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_toolbar.*

class MainActivity : View.OnClickListener, AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //check activated state
        if (isActivated()) {
            state.text = getString(R.string.success)
            state.setTextColor(ContextCompat.getColor(this, R.color.colorGreen))
        } else {
            state.text = getString(R.string.failed)
            state.setTextColor(ContextCompat.getColor(this, R.color.colorRed))
        }
        setSupportActionBar(toolbar)
        toolbar.title = title.toString()
    }

    /**
     * get isActivated state
     * We must hook this method in {@link SelfModule} and Return true
     */
    private fun isActivated(): Boolean {
        return false
    }

    /**
     * create OptionsMenu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mian_menu, menu)
        return true
    }

    /**
     * when menu selected
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.link_me -> {
                linkMe()
                return@onOptionsItemSelected true
            }
        }
        return@onOptionsItemSelected false
    }

    /**
     * onClick
     */
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.setting -> {
                startActivity(Intent(this, SettingActivity::class.java))
            }
        }
    }

    /**
     * open blued and go to user's profile
     */
    private fun linkMe() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.action = "android.intent.action.VIEW"
        val cn = ComponentName("com.soft.blued", "com.soft.blued.ui.welcome.FirstActivity")
        intent.component = cn
        intent.data = Uri.parse("blued://native.blued.cn?action=profile&enc=1&uid=9JKQZQ")
        startActivity(intent)
    }
}
