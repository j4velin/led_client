package de.j4velin.ledclient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class RulesAdapter(var data: List<Rule>) :
    RecyclerView.Adapter<RulesAdapter.Holder>() {

    class Holder(private val card: CardView, val name: TextView, val edit: View) :
        RecyclerView.ViewHolder(card)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {
        val card =
            LayoutInflater.from(parent.context).inflate(R.layout.rule, parent, false) as CardView
        card.setOnClickListener(ruleClickListener)
        val appName: TextView = card.findViewById(R.id.app)
        val edit: View = card.findViewById(R.id.edit)
        edit.findViewById<View>(R.id.cancel).setOnClickListener(cancelClickListener)
        return Holder(card, appName, edit)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text = data[position].appName
        holder.edit.visibility = View.GONE
    }

    override fun getItemCount() = data.size

    private val ruleClickListener = View.OnClickListener { view ->
        val edit = view.findViewById<View>(R.id.edit)
        edit.alpha = 0f
        edit.visibility = View.VISIBLE
        edit.animate().setDuration(500).alpha(1f)
    }

    private val cancelClickListener = View.OnClickListener { cancel ->
        val edit: View = cancel.parent as View
        edit.animate().setDuration(500).alpha(0f).withEndAction { edit.visibility = View.GONE }
    }
}