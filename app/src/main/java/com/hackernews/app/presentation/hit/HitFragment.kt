package com.hackernews.app.presentation.hit

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hackernews.app.data.local.entity.Hit
import com.hackernews.app.databinding.FragmentHitBinding
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsFailure
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsStatus
import com.hackernews.app.presentation.adapters.hit.HitAdapter
import com.hackernews.app.presentation.base.BaseFragment
import com.hackernews.app.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class HitFragment : BaseFragment<FragmentHitBinding>() {

    private val hitViewModel: HitViewModel by viewModel()
    private var hits: ArrayList<Hit> = arrayListOf()
    private val hitAdapter by lazy {
        HitAdapter(::onHitClickListener)
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?):
            FragmentHitBinding = FragmentHitBinding.inflate(inflater, container, false)

    override fun setupViews() {
        setupActions()
        setupAdapters()
        executeGetHits()
    }

    /** */
    private fun setupActions() {
        binding.swipeView.setOnRefreshListener(::onRefreshSwipeListener)
    }

    /** */
    private fun onRefreshSwipeListener() {
        executeGetHits()
    }

    /** */
    private fun setupAdapters() {
        binding.rvHits.apply {
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    LinearLayoutManager.VERTICAL
                )
            )
            layoutManager = LinearLayoutManager(requireContext())
            adapter = hitAdapter
        }

        hitAdapter.submitList(hits)
    }

    /** */
    private fun executeGetHits() {
        hitViewModel.getHitsAsLiveData().observe(viewLifecycleOwner, setHitsStatusObserver())
    }

    /** */
    private fun setHitsStatusObserver() =
        Observer<GetHitsStatus> {
            when (it) {
                is Status.Done -> manageGetHitsDone(it.value.hits)
                is Status.Failed -> manageGetHitsFailure(it.failure)
            }

            if (binding.swipeView.isRefreshing) {
                binding.swipeView.isRefreshing = false
            }
        }

    /** */
    private fun manageGetHitsFailure(failure: GetHitsFailure) {
        val message: String = when (failure) {
            is GetHitsFailure.DetailsFailure -> failure.details
        }
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    /** */
    private fun manageGetHitsDone(hits: List<Hit>) {
        setDataHitsRecyclerView(hits)
    }

    /** */
    private fun setDataHitsRecyclerView(data: List<Hit>) {
        hits.clear()
        hits.addAll(data)
        hitAdapter.notifyDataSetChanged()
    }

    /** */
    private fun onHitClickListener(hit: Hit) {
        //Setup webView
    }

}