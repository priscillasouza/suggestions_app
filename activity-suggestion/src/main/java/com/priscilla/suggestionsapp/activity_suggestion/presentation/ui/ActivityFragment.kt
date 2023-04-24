package com.priscilla.suggestionsapp.activity_suggestion.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.priscilla.suggestionsapp.activity_suggestion.databinding.FragmentActivityBinding
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import com.priscilla.suggestionsapp.activity_suggestion.extensions.formatCurrencyToBr
import com.priscilla.suggestionsapp.activity_suggestion.presentation.adapter.ListProgressActivityAdapter
import com.priscilla.suggestionsapp.activity_suggestion.presentation.viewmodel.ActivityViewModel
import com.priscilla.suggestionsapp.activity_suggestion.presentation.viewmodel.ActivityViewModel.ActivityState.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ActivityFragment : Fragment() {

    private lateinit var binding: FragmentActivityBinding
    private lateinit var listProgressActivityAdapter: ListProgressActivityAdapter
    private val activityViewModel: ActivityViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentActivityBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel.getActivity()
        activityViewModel.getProgressActivity()
        onObserver()
        setAdpaterListProgressActivity()
    }

    private fun onObserver() {
        activityViewModel.apply {
            lifecycleScope.launch {
                kotlin.runCatching {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        stateActivityRandom.collect {
                            when (it) {
                                is Loading -> showLoading(true)
                                is Success -> bindingActivityModel(it.activityModel)
                                is Error -> showError(it.message)
                                is Loaded -> showLoading(false)
                                else -> {}
                            }
                        }
                    }
                }
            }

            lifecycleScope.launchWhenStarted {
                stateListProgressActivity.collect {
                    when (it) {
                        is ActivityViewModel.ListActivityState.Loading -> showLoading(true)
                        is ActivityViewModel.ListActivityState.Success -> listAdapter(it.listActivityModel)
                        is ActivityViewModel.ListActivityState.Error -> showError(it.message)
                        is ActivityViewModel.ListActivityState.Loaded -> showLoading(false)
                        is ActivityViewModel.ListActivityState.Empty -> {}
                        else -> {}
                    }
                }
            }
        }
    }

    private fun showLoading(b: Boolean) {

    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message,
            Toast.LENGTH_SHORT)
            .show()
    }

    private fun bindingActivityModel(activityModel: ActivityModel) {
        binding.apply {
            textViewActivityType.text = activityModel.type
            textViewActivityDescription.text = activityModel.activity
            textViewActivityAcessibilityLabel.text = activityModel.accessibility.toString()
            textViewParticipantsLabel.text = activityModel.participants.toString()
            textViewPrice.text = activityModel.price.formatCurrencyToBr()
            setClicksCardActivity(activityModel)
            setClickButtonsCardActivity(activityModel)
        }
    }

    private fun setClickButtonsCardActivity(activityModel: ActivityModel) {
        binding.apply {
            buttonReject.setOnClickListener {
                activityViewModel.getActivity()
            }
            buttonAccept.setOnClickListener {
                activityViewModel.apply {
                    acceptActivity(activityModel)
                    getActivity()
                }
            }
        }
    }

    private fun setClicksCardActivity(activityModel: ActivityModel) {
        binding.apply {
            cardActivitySuggestion.apply {
                val click: View.OnClickListener?
                val visibility: Int
                if (activityModel.link.isNotBlank()) {
                    visibility = View.VISIBLE
                    click = View.OnClickListener {
                        val uri = Uri.parse(activityModel.link)
                        val browserIntent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(browserIntent)
                    }
                } else {
                    click = null
                    visibility = View.GONE
                }

                setOnClickListener(click)
                imageViewLink.visibility = visibility
            }
        }
    }

    private fun setAdpaterListProgressActivity() {
        binding.recyclerViewListProgressActivities.apply {
            listProgressActivityAdapter = ListProgressActivityAdapter()
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = listProgressActivityAdapter
        }
    }

    private fun listAdapter(list: List<ActivityModel>) {
        listProgressActivityAdapter.setList(list)
    }
}