package ru.andvl.avitotask

import android.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ticker
import ru.andvl.avitotask.NumbersFragment.*
import ru.andvl.avitotask.databinding.RecyclerItemBinding
import ru.andvl.avitotask.placeholder.PlaceholderContent
import ru.andvl.avitotask.placeholder.PlaceholderContent.PlaceholderItem
import java.util.*


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
@ObsoleteCoroutinesApi
class NumbersRecyclerViewAdapter(
    private val values: PlaceholderContent,
) : RecyclerView.Adapter<NumbersRecyclerViewAdapter.ViewHolder>() {

    val mJob = Job()
    private val mMainScope = CoroutineScope(Dispatchers.Main + mJob)

    init {
        val tickerChannel = ticker(delayMillis = 5000)
        mMainScope.launch {
            for (event in tickerChannel) {
                addNumber()
            }
        }
    }

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
        holder.idView.text = item.content.toString()
    }

    override fun getItemCount(): Int = values.ITEMS.size

    private fun addNumber() {
        values.addNext()
        notifyDataSetChanged()
    }

    companion object{
        private const val TAG = "ADAPTER"
    }

    inner class ViewHolder(binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: Button = binding.deleteButton

        init {
            contentView.setOnClickListener {
                removeAt(adapterPosition)
            }
        }

        private fun removeAt(position: Int) {
            values.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}