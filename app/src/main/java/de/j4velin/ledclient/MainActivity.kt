package de.j4velin.ledclient

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

val TAG = "LedClient"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val rulesAdapter = RulesAdapter(emptyList())
        val rulesViewModel: RulesViewModel by viewModels()
        val ruleRepository = RuleRepository()

        findViewById<RecyclerView>(R.id.list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = rulesAdapter
        }

        rulesViewModel.rules.observe(this, Observer<List<Rule>> { rules ->
            rulesAdapter.data = rules
            rulesAdapter.notifyDataSetChanged()
        })

        rulesViewModel.ruleRepository = ruleRepository

        fab.setOnClickListener { view ->
            // TODO add rule
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
