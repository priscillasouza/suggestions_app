package com.priscilla.suggestionsapp.activity_suggestion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.IActivityRepository
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.*

class ActivityViewModel(
    private val activityRepository: IActivityRepository,
) : ViewModel() {

    private var _stateActivityRandom = MutableStateFlow<ActivityState>(ActivityState.Empty)
    val stateActivityRandom: StateFlow<ActivityState> = _stateActivityRandom

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
                activityRepository.saveActivity(activityModel.copy(startTime = Date()))
            }catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    sealed class ActivityState {
        class Success(val activityModel: ActivityModel): ActivityState()
        class Error(val message: String): ActivityState()
        object Loading: ActivityState()
        object Loaded: ActivityState()
        object Empty: ActivityState()
    }
}