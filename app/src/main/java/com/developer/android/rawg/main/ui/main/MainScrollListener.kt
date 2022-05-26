package com.developer.android.rawg.main.ui.main

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import timber.log.Timber

class MainScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val loadNextPage: (Int) -> Unit
): RecyclerView.OnScrollListener() {

    private var isLoading = true
    private var totalLoadedItems = 0

    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItems = layoutManager.childCount
        val totalItems = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (totalItems == visibleItems) return

        if (totalItems > totalLoadedItems) {
            isLoading = false
            totalLoadedItems = totalItems
            return
        }

        val shouldLoadMore = totalItems - visibleItems <= firstVisibleItemPosition + 10
        if (!isLoading && shouldLoadMore) {
            currentPage++
            loadNextPage(currentPage)
            isLoading = true
        }
    }

    fun reset() {
        isLoading = false
        totalLoadedItems = 0
        currentPage = 1
    }
}