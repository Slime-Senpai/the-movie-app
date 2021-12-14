package fr.mbds.dtla.movieapp.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.mbds.dtla.movieapp.R

class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<Int>().apply {
        value = R.string.text_about
    }
    val text: LiveData<Int> = _text
}