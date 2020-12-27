package ru.andvl.avitotask

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.andvl.avitotask.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class NumbersFragment : Fragment() {

    private var columnCount = 2
    lateinit var callback: OnItemSelectedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = NumbersRecyclerViewAdapter(PlaceholderContent.ITEMS, this@NumbersFragment)
            }
        }
        return view
    }

    public interface OnItemSelectedListener {
        fun onItemSelected(pos: Int)
    }

    private fun setOnItemSelectedListener(callback: OnItemSelectedListener) {
        this.callback = callback
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            NumbersFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}