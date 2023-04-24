package com.priscilla.suggestionsapp.activity_suggestion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.IActivityRepository
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.Status
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class ActivityViewModel(
    private val activityRepository: IActivityRepository,
) : ViewModel() {

    private var _stateActivityRandom = MutableStateFlow<ActivityState>(ActivityState.Empty)
    val stateActivityRandom: StateFlow<ActivityState> = _stateActivityRandom

    private var _stateListProgressActivity = MutableStateFlow<ListActivityState>(ListActivityState.Empty)
    val stateListProgressActivity: StateFlow<ListActivityState> = _stateListProgressActivity

    fun getActivity() {
        _stateActivityRandom.value = ActivityState.Loading
        viewModelScope.launch {
            activityRepository.getActivity()
                .catch { exception ->
                    exception.printStackTrace()
                    _stateActivityRandom.value = ActivityState.Error("Ocorreu uma falha")
                }.collect {
                    _stateActivityRandom.value = ActivityState.Success(it)
                }
            _stateActivityRandom.value = ActivityState.Loaded
        }
    }

    fun acceptActivity(activityModel: ActivityModel) {
        viewModelScope.launch {
            try {
                activityRepository.saveActivity(activityModel.copy(startTime = Date(),
                    status = Status.STATUS_PROGRESS))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getProgressActivity() {
        viewModelScope.launch {
            _stateListProgressActivity.value = ListActivityState.Loading
            activityRepository.getProgressAcitivity()
                .catch { exception ->
                    exception.printStackTrace()
                    _stateListProgressActivity.value =
                        ListActivityState.Error("Ocorreu uma falha ao listar as atividades concluídas")
                }.collect {
                    if (it.isEmpty()) {
                        _stateListProgressActivity.value = ListActivityState.Empty
                    }
                    _stateListProgressActivity.value = ListActivityState.Success(it)

                }
        }
    }

    sealed class ActivityState {
        class Success(val activityModel: ActivityModel) : ActivityState()
        class Error(val message: String) : ActivityState()
        object Loading : ActivityState()
        object Loaded : ActivityState()
        object Empty : ActivityState()
    }

    sealed class ListActivityState {
        class Success(val listActivityModel: List<ActivityModel>) : ListActivityState()
        class Error(val message: String) : ListActivityState()
        object Loading : ListActivityState()
        object Loaded : ListActivityState()
        object Empty : ListActivityState()
    }
}