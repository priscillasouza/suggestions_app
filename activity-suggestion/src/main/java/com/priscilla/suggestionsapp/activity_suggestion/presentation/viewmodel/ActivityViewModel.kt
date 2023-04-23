package com.priscilla.suggestionsapp.activity_suggestion.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.IActivityRepository
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.model.ActivityModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ActivityViewModel(
    private val activityRepository: IActivityRepository,
) : ViewModel() {

    private var _getActivity = MutableStateFlow<ActivityState>(ActivityState.Empty)
    val getActivity: StateFlow<ActivityState> = _getActivity

    fun getActivity() {
        _getActivity.value = ActivityState.Loading
        viewModelScope.launch {
            activityRepository.getActivity()
                .catch { exception ->
                    exception.printStackTrace()
                    _getActivity.value = ActivityState.Error("Ocorreu uma falha")
                }.collect {
                _getActivity.value = ActivityState.Success(it)
            }
            _getActivity.value = ActivityState.Loaded
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