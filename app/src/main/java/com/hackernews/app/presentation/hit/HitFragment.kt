package com.hackernews.app.presentation.hit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.hackernews.app.data.local.entity.Hit
import com.hackernews.app.databinding.FragmentHitBinding
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsFailure
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsStatus
import com.hackernews.app.presentation.base.BaseFragment
import com.hackernews.app.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class HitFragment : BaseFragment<FragmentHitBinding>() {

    private val hitViewModel: HitViewModel by viewModel()

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?):
            FragmentHitBinding = FragmentHitBinding.inflate(inflater, container, false)

    override fun setupViews() {
        executeGetHits()
    }

    /** */
    private fun executeGetHits() {
        hitViewModel.getHitsAsLiveData().observe(viewLifecycleOwner, setHitsStatusObserver())
    }

    /** */
    private fun setHitsStatusObserver() =
        Observer<GetHitsStatus> {
            when (it) {
                is Status.Loading -> manageGetHitsLoading()
                is Status.Done -> manageGetHitsDone(it.value.hits)
                is Status.Failed -> manageGetHitsFailure(it.failure)
            }
        }

    /** */
    private fun manageGetHitsLoading() {
        binding.apply {
            viewLoading.visibility = View.VISIBLE
            rvHits.visibility = View.INVISIBLE
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
        binding.apply {
            rvHits.visibility = View.VISIBLE
            viewLoading.visibility = View.INVISIBLE
        }
        setDataHitsRecyclerView(hits)
    }

    /** */
    private fun setDataHitsRecyclerView(hits: List<Hit>) {
        Toast.makeText(requireContext(), "${hits.size}", Toast.LENGTH_SHORT).show()
    }

}