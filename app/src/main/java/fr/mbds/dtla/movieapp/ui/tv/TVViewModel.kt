package fr.mbds.dtla.movieapp.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.mbds.dtla.idbdata.data.TVShow
import fr.mbds.dtla.idbdata.repository.MovieRepository
import fr.mbds.dtla.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TVViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    private val _tvshows: MutableLiveData<List<TVShow>> = MutableLiveData()
    val tvshows: LiveData<List<TVShow>>
        get() = _tvshows

    fun getTVShows(categoryId: Int, language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getTVShows(categoryId, language)) {
                is Result.Succes -> {
                    _tvshows.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
}