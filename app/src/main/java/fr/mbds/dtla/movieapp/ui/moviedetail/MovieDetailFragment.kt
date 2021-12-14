package fr.mbds.dtla.movieapp.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import fr.mbds.dtla.movieapp.R
import fr.mbds.dtla.movieapp.databinding.FragmentMovieDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {

    private val viewModel: MovieDetailViewModel by viewModel()
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            movie.observe(viewLifecycleOwner, { movie ->
                binding.movieTitle.text = movie.name
                binding.contentDate.text = movie.date
                binding.contentVote.text = movie.vote
                binding.contentDescription.text = movie.description
                Picasso.get().load(movie.backdrop).into(binding.movieImg)
            })

            error.observe(viewLifecycleOwner, {
                binding.movieTitle.text = getString(R.string.error)
                binding.titleDate.text = ""
                binding.titleDescription.text = ""
                binding.titleVote.text = ""
            })

            getMovie(requireArguments().getString("movieId")!!.toInt(), getString(R.string.language))
        }
    }
}
