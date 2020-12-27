package ru.andvl.avitotask

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ru.andvl.avitotask.NumbersFragment.*

import ru.andvl.avitotask.placeholder.PlaceholderContent.PlaceholderItem
import ru.andvl.avitotask.databinding.RecyclerItemBinding
import ru.andvl.avitotask.placeholder.PlaceholderContent

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class NumbersRecyclerViewAdapter(
    private val values: PlaceholderContent,
    private val callback: OnItemSelectedListener
) : RecyclerView.Adapter<NumbersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            RecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values.ITEMS[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.content

    }

    override fun getItemCount(): Int = values.ITEMS.size

    fun addNumber() {
        values.addNext()
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: Button = binding.deleteButton

        init {
            contentView.setOnClickListener {
                callback.onItemSelected(idView.text.toString().toInt())
                notifyDataSetChanged()
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}