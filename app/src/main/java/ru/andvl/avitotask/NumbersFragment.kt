package ru.andvl.avitotask

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import kotlinx.coroutines.ObsoleteCoroutinesApi
import ru.andvl.avitotask.placeholder.PlaceholderContent
import kotlin.properties.Delegates

/**
 * A fragment representing a list of Items.
 */
class NumbersFragment : Fragment() {

    private var columnCount by Delegates.notNull<Int>()
    private lateinit var mRecyclerAdapter: NumbersRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        columnCount = if (resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) P_COLM else L_COLM

//        arguments?.let {
//            columnCount = it.getInt(ARG_COLUMN_COUNT)
//        }
    }

    @ObsoleteCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        mRecyclerAdapter = NumbersRecyclerViewAdapter(PlaceholderContent)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = mRecyclerAdapter
                val animation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
                layoutAnimation = animation
            }
        }
        return view
    }

    @ObsoleteCoroutinesApi
    override fun onPause() {
        super.onPause()
        mRecyclerAdapter.mJob.cancel()
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        private const val P_COLM = 2
        private const val L_COLM = 4

        @JvmStatic
        fun newInstance() =
            NumbersFragment()//.apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_COLUMN_COUNT, columnCount)
//                }
//            }
    }
}