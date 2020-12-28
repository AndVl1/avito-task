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
                Log.d("TICKER", "event")
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
        holder.idView.text = item.content
        holder.contentView.setOnClickListener {
            values.removeAt(position)
            notifyDataSetChanged()
        }
        setFadeAnimation(holder.binding.root)
    }

    override fun getItemCount(): Int = values.ITEMS.size

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.clearAnimation()
    }

    private fun addNumber() {
        values.addNext()
        notifyDataSetChanged()
    }

    private fun addAnimation(viewToAnimate: View, position: Int) {
        if (position > values.ITEMS.lastIndex-1) {
            val animation: Animation = AnimationUtils.loadAnimation(
                viewToAnimate.context,
                R.anim.slide_in_left
            )
            viewToAnimate.startAnimation(animation)
        }
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = FADE_DURATION
        view.startAnimation(anim)
    }

    companion object{
        private const val FADE_DURATION = 1000L
    }

    inner class ViewHolder(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: Button = binding.deleteButton

        fun clearAnimation() {
            binding.root.clearAnimation()
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}