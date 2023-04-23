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
import com.priscilla.suggestionsapp.activity_suggestion.databinding.FragmentActivityBinding
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import com.priscilla.suggestionsapp.activity_suggestion.extensions.formatCurrencyToBr
import com.priscilla.suggestionsapp.activity_suggestion.presentation.viewmodel.ActivityViewModel
import com.priscilla.suggestionsapp.activity_suggestion.presentation.viewmodel.ActivityViewModel.ActivityState.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ActivityFragment : Fragment() {

    private lateinit var binding: FragmentActivityBinding
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
        onObserver()
    }

    private fun onObserver() {
        activityViewModel.apply {
            lifecycleScope.launch {
                kotlin.runCatching {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        getActivity.collect {
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
        }
    }

    private fun setClicksCardActivity(activityModel: ActivityModel) {
        binding.apply {
            cardActivitySuggestion.apply {
                var click: View.OnClickListener? = null
                var visibility: Int = View.GONE
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
}