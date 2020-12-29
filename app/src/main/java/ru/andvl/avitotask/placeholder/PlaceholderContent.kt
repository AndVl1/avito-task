package ru.andvl.avitotask.placeholder

import android.util.Log
import java.util.*
import kotlin.collections.ArrayList

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    private val deletedNumbers : Queue<Int> = LinkedList()

    private const val INITIAL_COUNT = 15

    val size: Int get() = size()

    private var biggest: Int

    init {
        // Add some sample items.
        for (i in 1..INITIAL_COUNT) {
            addItem(createPlaceholderItem(i))
        }
        biggest = ITEMS.size
    }

    fun size() : Int = ITEMS.size

    fun addNext() {
        addItem(createPlaceholderItem(deletedNumbers.poll() ?: ++biggest))
        Log.d(TAG, "add $biggest")
    }

    fun removeAt(position: Int) {
        val toRemove = ITEMS.removeAt(position)
//        if (toRemove.content == biggest) {
//            biggest--
//        }
        deletedNumbers.add(toRemove.content)
    }

    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
    }

    private fun createPlaceholderItem(num: Int): PlaceholderItem {
        return PlaceholderItem(num)
    }

    /**
     * A placeholder item representing a piece of content.
     */
    data class PlaceholderItem(val content: Int): Comparable<PlaceholderItem> {
        override fun toString(): String = content.toString()
        override fun compareTo(other: PlaceholderItem): Int = this.content.compareTo(other.content)
    }
    private const val TAG = "PLACEHOLDER"
}