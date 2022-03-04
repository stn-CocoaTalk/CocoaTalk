package com.stn.cocoatalk.presentation.initial

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(

): ViewModel() {

    private val _inputText = mutableStateOf("")
    val inputText: State<String> = _inputText

    private val _toast = MutableSharedFlow<String>()
    val toast: SharedFlow<String> = _toast.asSharedFlow()

    fun textChange(text: String) {
        _inputText.value = text
    }

    fun validateInput(): Boolean {
        return _inputText.value.isNotBlank()
    }

    fun showSnackBar() {
        viewModelScope.launch {
            _toast.emit("You must input your email address.")
        }
    }
}