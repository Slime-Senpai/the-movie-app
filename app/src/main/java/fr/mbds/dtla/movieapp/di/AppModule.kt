package fr.mbds.dtla.movieapp.di

import android.content.Context
import fr.mbds.dtla.movieapp.ui.home.HomeViewModel
import fr.mbds.dtla.movieapp.ui.hometv.HomeTVViewModel
import fr.mbds.dtla.movieapp.ui.moviedetail.MovieDetailViewModel
import fr.mbds.dtla.movieapp.ui.movies.MovieViewModel
import fr.mbds.dtla.movieapp.ui.tv.TVViewModel
import fr.mbds.dtla.movieapp.ui.tvdetail.TVShowDetailViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named("API_KEY")) {
        "507a86e6d98ae2b2cd600e594ee02637"
    }

    single(named("BASE_URL")) {
        "https://api.themoviedb.org/3/"
    }

    single(named("APP_PREFS")) {
        androidContext().getSharedPreferences("app_private", Context.MODE_PRIVATE)
    }

    viewModel {
        HomeViewModel(repository = get())
    }

    viewModel {
        MovieViewModel(repository = get())
    }

    viewModel {
        MovieDetailViewModel(repository = get())
    }

    viewModel {
        HomeTVViewModel(repository = get())
    }

    viewModel {
        TVViewModel(repository = get())
    }

    viewModel {
        TVShowDetailViewModel(repository = get())
    }
}