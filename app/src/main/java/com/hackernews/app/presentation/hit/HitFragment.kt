package com.hackernews.app.presentation.hit

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hackernews.app.databinding.FragmentHitBinding
import com.hackernews.app.domain.hit.entity.Hit
import com.hackernews.app.domain.hit.uses_case.delete_hits.DeleteHitFailure
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsFailure
import com.hackernews.app.domain.hit.uses_case.get_hits.GetHitsStatus
import com.hackernews.app.presentation.adapters.hit.CallBackItemTouch
import com.hackernews.app.presentation.adapters.hit.HitAdapter
import com.hackernews.app.presentation.adapters.hit.HitTouchHelper
import com.hackernews.app.presentation.base.BaseFragment
import com.hackernews.app.presentation.detail.HitDetailActivity
import com.hackernews.app.utils.Status
import kotlinx.android.synthetic.main.fragment_hit.*
import org.koin.android.viewmodel.ext.android.viewModel

class HitFragment : BaseFragment<FragmentHitBinding>(), CallBackItemTouch {

    private val hitViewModel: HitViewModel by viewModel()
    private val hitAdapter by lazy {
        HitAdapter(::onHitClickListener)
    }

    override fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?):
            FragmentHitBinding = FragmentHitBinding.inflate(inflater, container, false)

    override fun setupViews() {
        setupAdapters()
        executeGetHits()
        setupPullToRefresh()
    }

    /** */
    private fun setupPullToRefresh() {
        binding.swipeView.setOnRefreshListener {
            binding.swipeView.isRefreshing = true
            executeGetHits()
        }
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

        val callBack: ItemTouchHelper.Callback = HitTouchHelper(this)
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(binding.rvHits)
    }

    /** */
    private fun executeGetHits() {
        hitViewModel.getHitsAsLiveData().observe(viewLifecycleOwner, setHitsStatusObserver())
    }

    /** */
    private fun setHitsStatusObserver() =
        Observer<GetHitsStatus> {
            when (it) {
                is Status.Loading -> {
                    if (!binding.swipeView.isRefreshing) {
                        showProgressBar()
                    }
                }
                is Status.Failed -> manageGetHitsFailure(it.failure)
                is Status.Done -> manageGetHitsDone(it.value.hits)
            }
        }

    /** */
    private fun manageGetHitsDone(hits: List<Hit>) {
        if (hits.isNotEmpty()) {
            setDataHitsRecyclerView(hits)
        } else {
            tvEmptyMessage.visibility = View.VISIBLE
        }

        hideProgressBar()
    }

    /** */
    private fun setDataHitsRecyclerView(data: List<Hit>) {
        hitAdapter.submitList(data)
    }

    /** */
    private fun onHitClickListener(hit: Hit) {
        val intent = Intent(requireContext(), HitDetailActivity::class.java)
        intent.putExtra("hit", hit)
        startActivity(intent)
    }

    /** */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) {
        hitViewModel.deleteHitAsLiveData(hitAdapter.currentList[position])
            .observe(viewLifecycleOwner, {
                when (it) {
                    is Status.Done -> executeGetHits()
                    is Status.Failed -> manageDeleteHitFailure(it.failure)
                }
            })
    }

    /** */
    private fun manageGetHitsFailure(failure: GetHitsFailure) {
        val message: String = when (failure) {
            is GetHitsFailure.DetailsFailure -> failure.details
        }
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        hideProgressBar()
    }

    /** */
    private fun manageDeleteHitFailure(failure: DeleteHitFailure) {
        val message: String = when (failure) {
            is DeleteHitFailure.DetailsFailure -> failure.details
        }
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    /** */
    private fun showProgressBar() {
        binding.pbProgress.visibility = View.VISIBLE
    }

    /** */
    private fun hideProgressBar() {
        if(binding.swipeView.isRefreshing) {
            binding.swipeView.isRefreshing = false
        }

        binding.pbProgress.visibility = View.GONE
    }

}