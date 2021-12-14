package fr.mbds.dtla.movieapp.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.mbds.dtla.movieapp.R
import fr.mbds.dtla.movieapp.databinding.FragmentMoviesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModel()
    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            movies.observe(viewLifecycleOwner, {
                binding.categoryList.adapter = MovieAdapter(it) { movie ->
                    val direction = MovieFragmentDirections.actionMoviesToMovieDetail(movie.id.toString())
                    findNavController().navigate(direction)
                }
            })

            error.observe(viewLifecycleOwner, {
                //afficher l'erreur
            })

            (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_movies) + " - " + requireArguments().getString("categoryName")

            getMovies(requireArguments().getString("categoryId")!!.toInt(), getString(R.string.language))
        }
    }
}
