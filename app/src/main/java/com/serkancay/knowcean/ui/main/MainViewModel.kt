package com.serkancay.knowcean.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serkancay.knowcean.network.model.Page
import com.serkancay.knowcean.network.model.Response
import com.serkancay.knowcean.repository.WikiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val wikiRepository: WikiRepository) : ViewModel() {

    private val _pageState = mutableStateOf<Response<Page>>(Response.Loading)
    val pageState: State<Response<Page>> get() = _pageState

    fun getRandomPage() {
        viewModelScope.launch {
            wikiRepository.getRandomPage().collect { response ->
                _pageState.value = response
            }
        }
    }

}