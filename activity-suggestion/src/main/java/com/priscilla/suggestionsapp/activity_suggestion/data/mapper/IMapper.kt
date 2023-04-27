package com.priscilla.suggestionsapp.activity_suggestion.data.mapper

interface IMapper<E, T> {
    fun transform(activityEntity: E): T
    fun transform(list: List<E>): List<T> = list.map { transform(it) }
}