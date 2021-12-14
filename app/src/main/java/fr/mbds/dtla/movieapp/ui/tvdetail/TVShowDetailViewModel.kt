package fr.mbds.dtla.movieapp.ui.tvdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.mbds.dtla.idbdata.data.Category
import fr.mbds.dtla.idbdata.data.Movie
import fr.mbds.dtla.idbdata.data.TVShow
import fr.mbds.dtla.idbdata.data.Token
import fr.mbds.dtla.idbdata.repository.MovieRepository
import fr.mbds.dtla.idbdata.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TVShowDetailViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String>
        get() = _error

    private val _tvshow: MutableLiveData<TVShow> = MutableLiveData()
    val tvshow: LiveData<TVShow>
        get() = _tvshow

    fun getTVShow(tvshowId: Int, language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.getTVShow(tvshowId, language)) {
                is Result.Succes -> {
                    _tvshow.postValue(result.data)
                }
                is Result.Error -> {
                    _error.postValue(result.message)
                }
            }
        }
    }
}