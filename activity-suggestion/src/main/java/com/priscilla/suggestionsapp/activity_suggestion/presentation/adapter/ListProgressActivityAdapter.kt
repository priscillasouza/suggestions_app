package com.priscilla.suggestionsapp.activity_suggestion.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.priscilla.suggestionsapp.activity_suggestion.databinding.ProgressActivitiesItemBinding
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import com.priscilla.suggestionsapp.activity_suggestion.extensions.differStartTime
import com.priscilla.suggestionsapp.activity_suggestion.extensions.formatCurrencyToBr

class ListProgressActivityAdapter :
    RecyclerView.Adapter<ListProgressActivityAdapter.ActivityViewHolder>() {

    private var progressActivityList = arrayListOf<ActivityModel>()
    private var onClickButtonGivUp: ((ActivityModel) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val binding =
            ProgressActivitiesItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        return ActivityViewHolder(binding, onClickButtonGivUp)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.onBind(progressActivityList[position])
    }

    override fun getItemCount(): Int {
        return progressActivityList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<ActivityModel>) {
        if (list.isNotEmpty()) {
            progressActivityList.clear()
            progressActivityList.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun setOnClickButtonGivUp(run: ((ActivityModel) -> Unit?)?) {
        onClickButtonGivUp = run
    }

    class ActivityViewHolder(
        private val layout: ProgressActivitiesItemBinding,
        private val onClickButtonGivUp: ((ActivityModel) -> Unit?)?
    ) :
        RecyclerView.ViewHolder(layout.root) {
        fun onBind(activityModel: ActivityModel) {
            layout.apply {
                textViewActivityType.text = activityModel.type
                textViewActivityDescription.text = activityModel.activity
                textViewActivityAcessibilityLabel.text = activityModel.accessibility.toString()
                textViewParticipantsLabel.text = activityModel.participants.toString()
                textViewPrice.text = activityModel.price.formatCurrencyToBr()
                textViewStartTimeLabel.text = activityModel.startTime?.differStartTime()
                cardItemListProgressActivity.apply {
                    buttonGiveUp.setOnClickListener {
                        onClickButtonGivUp?.invoke(activityModel)
                    }
                }
            }
        }
    }
}