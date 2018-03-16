package com.shenhua.searchview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioGroup
import com.shenhua.libs.searchveiw.SearchView
import com.shenhua.libs.searchveiw.StrategyCircle2Bar
import com.shenhua.libs.searchveiw.StrategyCircle2Line

class MainActivity : AppCompatActivity() {

    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.searchView)
        searchView.setStrategy(StrategyCircle2Line())
        findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_circle2Line -> searchView.setStrategy(StrategyCircle2Line())
                R.id.rb_circle2Bar -> searchView.setStrategy(StrategyCircle2Bar())
            }
        }
    }

    fun start(view: View) {
        searchView.startAnim()
    }

    fun reset(view: View) {
        searchView.resetAnim()
    }
}
