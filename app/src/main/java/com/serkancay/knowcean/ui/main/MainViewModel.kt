package com.serkancay.knowcean.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serkancay.knowcean.network.model.Page
import com.serkancay.knowcean.network.model.Response
import com.serkancay.knowcean.repository.WikiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val wikiRepository: WikiRepository) : ViewModel() {

    private val _pageStateFlow: MutableStateFlow<Response<Page>> = MutableStateFlow(Response.Loading)
    val pageStateFlow: StateFlow<Response<Page>> get() = _pageStateFlow

    fun getRandomPage() {
        viewModelScope.launch {
            wikiRepository.getRandomPage().collect { response ->
                _pageStateFlow.value = response

            }
        }
    }

}