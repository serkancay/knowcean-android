package com.serkancay.knowcean.ui.main

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serkancay.knowcean.constant.PreferencesKeys
import com.serkancay.knowcean.network.model.Page
import com.serkancay.knowcean.network.model.Response
import com.serkancay.knowcean.repository.WikiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val wikiRepository: WikiRepository,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    private val _pageStateFlow: MutableStateFlow<Response<Page>> =
        MutableStateFlow(Response.Loading)
    val pageStateFlow: StateFlow<Response<Page>> get() = _pageStateFlow
    fun getRandomPage() {
        viewModelScope.launch {
            wikiRepository.getRandomPage().collect { response ->
                _pageStateFlow.value = response

            }
        }
    }

    fun selectLanguage(code: String) {
        viewModelScope.launch {
            dataStore.edit {
                it[PreferencesKeys.LANGUAGE] = code
            }
        }
    }

}