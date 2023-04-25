package com.priscilla.suggestionsapp.activity_suggestion.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.priscilla.suggestionsapp.activity_suggestion.databinding.FinishedActivitiesItemBinding
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import com.priscilla.suggestionsapp.activity_suggestion.extensions.formatCurrencyToBr

class ListFinishedActivitiesAdapter :
    RecyclerView.Adapter<ListFinishedActivitiesAdapter.ActivityViewHolder>() {

    private var finishedActivityList = arrayListOf<ActivityModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val binding =
            FinishedActivitiesItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        return ActivityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.onBind(finishedActivityList[position])
    }

    override fun getItemCount(): Int {
        return finishedActivityList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(listFinished: List<ActivityModel>) {
        if (listFinished.isNotEmpty()) {
            finishedActivityList.clear()
            finishedActivityList.addAll(listFinished)
            notifyDataSetChanged()
        }
    }

    class ActivityViewHolder(
        private val layout: FinishedActivitiesItemBinding,
    ) :
        RecyclerView.ViewHolder(layout.root) {
        fun onBind(activityModel: ActivityModel) {
            layout.apply {
                textViewActivityType.text = activityModel.type
                textViewActivityDescription.text = activityModel.activity
                textViewActivityAcessibilityLabel.text = activityModel.accessibility.toString()
                textViewParticipantsLabel.text = activityModel.participants.toString()
                textViewPrice.text = activityModel.price.formatCurrencyToBr()
                textViewSpentTimeLabel.text = activityModel.spentTime?.toString()
            }
        }
    }
}