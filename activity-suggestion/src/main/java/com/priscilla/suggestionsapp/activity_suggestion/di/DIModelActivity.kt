package com.priscilla.suggestionsapp.activity_suggestion.di

import com.priscilla.suggestionsapp.activity_suggestion.data.mapper.MapResponseToModel
import com.priscilla.suggestionsapp.activity_suggestion.data.repository.ActivityRepositoryImpl
import com.priscilla.suggestionsapp.activity_suggestion.domain.repository.IActivityRepository
import com.priscilla.suggestionsapp.activity_suggestion.presentation.viewmodel.ActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val activityModule = module {

    viewModel {
        ActivityViewModel(get(named("ActivityRepository")))
    }

    single<IActivityRepository>(named("ActivityRepository")) {
        ActivityRepositoryImpl(get(), get(), get())
    }

    single {
        MapResponseToModel()
    }
}