package com.developer.android.rawg.main.ui.main.adapter

import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.developer.android.rawg.R
import com.developer.android.rawg.common.ui.recyclerview.PagingState
import com.developer.android.rawg.main.model.GameTypes
import timber.log.Timber
import kotlin.Int


class Int(
    private val onGameItemClicked: (GameTypes.FullGame) -> Unit,
    private val onFailedListener: () -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = mutableListOf<GameTypes?>()
    private var pagingState: PagingState = PagingState.Idle

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            R.layout.game_item -> MainViewHolder(parent, onGameItemClicked)
            R.layout.progress_bar -> ProgressViewHolder(parent)
            R.layout.game_item_error -> ErrorViewHolder(parent)
            else -> throw IllegalStateException("Unknown view type: $viewType")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(item = data[position] as GameTypes.FullGame)
            is ErrorViewHolder -> {
                val state = pagingState
                if (state is PagingState.Error) holder.bind(state, onFailedListener)
            }
            is ProgressViewHolder -> Unit
        }
        val anim: Animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.all_games_animation)
        holder.itemView.startAnimation(anim)
    }

    fun setPagingState(newState: PagingState) {
        if (pagingState::class.java == newState::class.java) return
        val shouldHasExtraItem = newState !is PagingState.Idle
        val hasExtraItem = pagingState !is PagingState.Idle

        pagingState = newState

        val count = itemCount

        when {
            shouldHasExtraItem && hasExtraItem -> notifyItemChanged(count - 1)
            !shouldHasExtraItem && hasExtraItem -> notifyItemRemoved(count - 1)
            shouldHasExtraItem && !hasExtraItem -> notifyItemInserted(count)
        }
    }

    override fun getItemViewType(position: Int): Int = when {
        pagingState is PagingState.Idle || position < itemCount - 1 -> R.layout.game_item
        pagingState is PagingState.Loading || pagingState is PagingState.InitialLoading -> R.layout.progress_bar
        else -> R.layout.game_item_error
    }

    override fun getItemCount() = data.size

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) {
        when (holder) {
            is MainViewHolder -> holder.detach()
        }
    }

    fun setItems(games: List<GameTypes?>) {
        Timber.i("$games")
        data.addAll(games)
        notifyDataSetChanged()
    }

    fun clearItems() {
        val itemsCount = data.size
        data.clear()
        notifyItemRangeRemoved(0, itemsCount)
    }

}