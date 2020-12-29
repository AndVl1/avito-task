package ru.andvl.avitotask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ticker
import ru.andvl.avitotask.databinding.RecyclerItemBinding
import ru.andvl.avitotask.placeholder.PlaceholderContent
import ru.andvl.avitotask.placeholder.PlaceholderContent.PlaceholderItem


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
    private var lastIndex = 15

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
        setAnimation(holder.binding.root, position)
    }

    override fun getItemCount(): Int = values.ITEMS.size

    private fun addNumber() {
        values.addNext()
        notifyItemInserted(values.size)
    }

    private fun setAnimation(view: View, position: Int) {
        if (position > lastIndex) {
            val animation = AnimationUtils.loadAnimation(view.context, R.anim.item_animation_slide_up)
            view.startAnimation(animation)
            lastIndex = position
        }
    }

    companion object{
        private const val TAG = "ADAPTER"
    }

    inner class ViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        private val contentView: Button = binding.deleteButton

        init {
            contentView.setOnClickListener {
                removeAt(adapterPosition)
            }
        }

        private fun removeAt(position: Int) {
            values.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
            lastIndex--
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}