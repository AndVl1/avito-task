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

    /**
     * A map of sample (placeholder) items, by ID.
     */
    private val ITEM_MAP: MutableMap<Int, PlaceholderItem> = HashMap()

    private val deletedNumbers : Queue<Int> = LinkedList()

    private const val INITIAL_COUNT = 15

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
        if (toRemove.id == biggest) {
            biggest--
        }
        deletedNumbers.add(toRemove.content.toInt())
    }



    private fun addItem(item: PlaceholderItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createPlaceholderItem(position: Int): PlaceholderItem {
        return PlaceholderItem(size(), "$position")
    }

    /**
     * A placeholder item representing a piece of content.
     */
    data class PlaceholderItem(val id: Int, val content: String): Comparable<PlaceholderItem> {
        override fun toString(): String = content
        override fun compareTo(other: PlaceholderItem): Int = this.id.compareTo(other.id)
    }
    private const val TAG = "PLACEHOLDER"
}